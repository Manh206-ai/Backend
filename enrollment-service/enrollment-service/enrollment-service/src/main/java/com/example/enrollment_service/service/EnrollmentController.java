package com.example.enrollment_service.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private StudentClient studentClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public ResponseEntity<String> createEnrollment(@RequestParam int studentId, @RequestParam String courseId) {
        try {
            ResponseEntity<String> response = studentClient.callStudentService(studentId);

            if (response.getStatusCode().is2xxSuccessful()) {
                String message = "Sinh vien " + studentId + " vua dang ky mon " + courseId;
                rabbitTemplate.convertAndSend("enrollmentQueue", message);
                return ResponseEntity.ok("Dang ky thanh cong mon " + courseId + " cho sinh vien " + studentId);
            }
        } catch (org.springframework.web.client.ResourceAccessException e) {
            return ResponseEntity.status(504).body("Qua thoi gian cho phan hoi tu Student Service (Timeout)!");
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(404).body("That bai: Ma sinh vien khong ton tai!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Loi ket noi den Student Service!");
        }
        return ResponseEntity.badRequest().build();
    }
}
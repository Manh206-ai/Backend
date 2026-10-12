package com.example.Student.Service.controller;

import com.example.Student.Service.model.Student;
import com.example.Student.Service.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // ===================== KHỞI TẠO SERVICE =====================

    // GET /api/students/hello
    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of("message", "Welcome to Student Service");
    }

    // GET /api/students/info
    @GetMapping("/info")
    public Map<String, String> info() {
        return Map.of(
                "service", "Student Service",
                "version", "1.0",
                "status", "UP");
    }

    // ===================== CRUD =====================

    // GET /api/students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAll();
    }

    // GET /api/students/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        // Gọi sang Service để lấy thông tin sinh viên theo ID
        Student student = studentService.getById(id);

        if (student != null) {
            return ResponseEntity.ok(student); // tìm thấy -> 200
        }
        return ResponseEntity.notFound().build(); // không có -> 404
    }

    // POST /api/students
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student created = studentService.add(student);
        return ResponseEntity.status(201).body(created);
    }

    // PUT /api/students/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updated = studentService.update(id, student);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/students/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        boolean removed = studentService.delete(id);
        if (!removed) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
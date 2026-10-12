package com.example.enrollment_service.service;

import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.http.ResponseEntity;

@Component
public class StudentClient {

    private final RestClient restClient;

    public StudentClient(RestClient restClient) {
        this.restClient = restClient;
    }

    // Đã đổi value thành retryFor để khắc phục cảnh báo
    @Retryable(maxAttempts = 3, retryFor = Exception.class)
    public ResponseEntity<String> callStudentService(int studentId) {
        return restClient.get()
                .uri("http://localhost:8080/api/students/" + studentId)
                .retrieve()
                .toEntity(String.class);
    }
}
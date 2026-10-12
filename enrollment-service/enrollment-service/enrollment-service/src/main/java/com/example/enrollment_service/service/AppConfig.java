package com.example.enrollment_service.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {

    @Bean
    public RestClient restClient() {
        // Sử dụng SimpleClientHttpRequestFactory thay thế
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000); // Timeout kết nối 3 giây
        factory.setReadTimeout(3000); // Timeout đọc dữ liệu 3 giây

        return RestClient.builder()
                .requestFactory(factory)
                .build();
    }
}
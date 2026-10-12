package com.example.enrollment_service.service;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String QUEUE_NAME = "enrollment.queue";

    @Bean
    public Queue enrollmentQueue() {
        return new Queue(QUEUE_NAME, true);
    }
}
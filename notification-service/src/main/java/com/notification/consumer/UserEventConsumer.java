package com.notification.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.common.dto.UserEvent;
import com.notification.service.EmailService;

@Service
public class UserEventConsumer {
    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "user-welcome", groupId = "notification-group")
    public void consume(UserEvent event) {
        try {
            System.out.println("Received event in user: " + event);
            emailService.sendWelcome(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.notification.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.common.dto.UserEvent;
import com.notification.service.EmailService;

public class UserEventConsumer {
    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "user-welcome", groupId = "notificarion-group")
    public void consume(UserEvent event){
        emailService.sendWelcome(event);
    }
}

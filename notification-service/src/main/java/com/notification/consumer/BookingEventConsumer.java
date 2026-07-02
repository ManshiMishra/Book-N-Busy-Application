package com.notification.consumer;

import com.common.dto.BookingEvent;
import com.notification.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookingEventConsumer {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "booking-confirmed", groupId = "notification-group")
    public void consume(BookingEvent event) {

        try {
            System.out.println("Received event in booking: " + event);
            emailService.send(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
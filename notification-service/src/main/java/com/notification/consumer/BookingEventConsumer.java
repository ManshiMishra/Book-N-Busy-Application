package com.notification.consumer;

import com.common.dto.BookingEvent;
import com.notification.service.EmailService;
import com.notification.service.SmsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookingEventConsumer {

    @Autowired
    private EmailService emailService;

    @Autowired
    private SmsService smsService;

    @KafkaListener(topics = "booking-confirmed", groupId = "notification-group")
    public void consume(BookingEvent event) {

        System.out.println("Received event: " + event);

        emailService.send(event);
        smsService.send(event);
    }
}
package com.notification.service;

import org.springframework.stereotype.Service;
import com.common.dto.BookingEvent;

@Service
public class EmailService {
    public void send(BookingEvent event) {
        System.out.println("Email sent to " + event.getEmail());
    }
}
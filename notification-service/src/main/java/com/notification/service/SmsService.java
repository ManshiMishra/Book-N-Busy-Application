package com.notification.service;

import org.springframework.stereotype.Service;
import com.common.dto.BookingEvent;

@Service
public class SmsService {
    public void send(BookingEvent event) {
        System.out.println("SMS sent to " + event.getPhone());
    }
}
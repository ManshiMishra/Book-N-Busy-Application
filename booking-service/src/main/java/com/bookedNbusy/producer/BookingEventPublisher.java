package com.bookedNbusy.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.common.dto.BookingEvent;

@Service
public class BookingEventPublisher {

    @Autowired
    private KafkaTemplate<String, BookingEvent> kafkaTemplate;

    public void publish(BookingEvent event) {
        kafkaTemplate.send("booking-confirmed", event);
    }
}
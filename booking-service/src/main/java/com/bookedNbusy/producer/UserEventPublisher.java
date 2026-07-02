package com.bookedNbusy.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.common.dto.UserEvent;

@Service
public class UserEventPublisher {
    @Autowired
    private KafkaTemplate<String, UserEvent> userKafkaTamplate;

    public void publish(UserEvent event) {
        userKafkaTamplate.send("user-welcome", event)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        System.out.println("Sent to topic: "
                                + result.getRecordMetadata().topic()
                                + ", offset: "
                                + result.getRecordMetadata().offset());
                    } else {
                        ex.printStackTrace();
                    }
                });
    }

}

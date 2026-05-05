package com.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.common.dto.BookingEvent;
import com.common.dto.UserEvent;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void send(BookingEvent event) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(event.getEmail());
        message.setSubject("Booking Confirmed! ");
        message.setText(
                "Your booking is confirmed!\n\n" +
                "Movie: " + event.getMovieName() + "\n" +
                "Seats: " + event.getSeats()
        );

        mailSender.send(message);

        System.out.println("Email sent to " + event.getEmail());
    }

    public void sendWelcome(UserEvent uEvent){
        SimpleMailMessage msg=new SimpleMailMessage();

        msg.setSubject("Hii "+uEvent.getName()+" !");
        msg.setFrom("bookednbusyshow@gmail.com");
        msg.setTo(uEvent.getEmail().toString());
        msg.setText("Welcome to BookedNBusy app, you have succesfully registered with us!");
        mailSender.send(msg);
    }
}
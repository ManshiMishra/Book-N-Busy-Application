package com.bookedNbusy.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bookedNbusy.ResponseDTO.GetTicketResponse;
import com.bookedNbusy.Entity.Ticket;
import com.bookedNbusy.Entity.User;
import com.bookedNbusy.Repository.UserRepository;
import com.bookedNbusy.RequestDTO.AddNewUserRequest;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    public String addNewUser(AddNewUserRequest addNewUserRequest){
        User newUser=User.builder()
                    .userName(addNewUserRequest.getUserName())
                    .phoneNumber(addNewUserRequest.getPhoneNumber())
                    .userEmail(addNewUserRequest.getUserEmail())
                    .build();

        userRepository.save(newUser);
        SimpleMailMessage msg=new SimpleMailMessage();
        msg.setSubject("Hii "+addNewUserRequest.getUserName()+" !");
        msg.setFrom("bookednbusyshow@gmail.com");
        msg.setTo(addNewUserRequest.getUserEmail().toString());
        msg.setText("Welcome to BookedNBusy app, you have succesfully registered with us!");
        mailSender.send(msg);

        return "User Registered!";
    }

    public GetTicketResponse viewBookedTicket(String number, Integer ticketId) {

        Optional<User> user=userRepository.findUserByPhoneNumber(number);
        Ticket searchedTicket=user.get().getListOfTickets().getLast();
        GetTicketResponse gtr=GetTicketResponse.builder()
                            .movieName(searchedTicket.getShow().getMovie().getMovieName())
                            .showTime(searchedTicket.getShow().getShowTime().toString()+searchedTicket.getShow().getShowDate().toString())
                            .theaterInfo(searchedTicket.getShow().getTheaterShow().getTheaterName()+" "+searchedTicket.getShow().getTheaterShow().getAddress())
                            .seatDetail(searchedTicket.getBookedSeat())
                            .food(false)
                            .amount(searchedTicket.getTotalAmountPaid())
                            .build();
        return gtr;
    }
}

package com.bookedNbusy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookedNbusy.ResponseDTO.GetTicketResponse;
import com.bookedNbusy.RequestDTO.AddNewUserRequest;
import com.bookedNbusy.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/app/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/addNewUser")
    public ResponseEntity<String> requestAddUserController(@RequestBody AddNewUserRequest addNewUserRequest) {

        try {
            String msg=userService.addNewUser(addNewUserRequest);
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("viewTicket")
    public GetTicketResponse getUserBookedTicket(@RequestParam("phoneNumber") String number,@RequestParam("ticketId") Integer ticketId) {
        
           GetTicketResponse getTicket= userService.viewBookedTicket(number,ticketId);
        
        return getTicket;
    }
    
    
}

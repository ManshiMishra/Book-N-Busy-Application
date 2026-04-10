package com.bookedNbusy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookedNbusy.RequestDTO.AddBookTicketRequest;
import com.bookedNbusy.Service.BookTicketService;

@RestController
@RequestMapping("/app/ticket")
public class BookTicketController {
    
    @Autowired
    private BookTicketService bookTicketService;

    @PostMapping("/ticketBook")
    public ResponseEntity<String> requestAddMovieController(@RequestBody AddBookTicketRequest addBookTicketRequest){
        try {
            String msg = bookTicketService.bookTicketWithShowId(addBookTicketRequest);
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Bad request!");
        }
    }
}

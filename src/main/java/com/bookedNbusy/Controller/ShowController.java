package com.bookedNbusy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookedNbusy.RequestDTO.AddShowRequest;
import com.bookedNbusy.RequestDTO.AddShowSeatRequest;
import com.bookedNbusy.RequestDTO.AddTheaterSeatRequest;
import com.bookedNbusy.Service.ShowService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/app/show")
public class ShowController {
    
    @Autowired
    private ShowService showService;

    @PostMapping("addShow")
    public ResponseEntity<String> requestAddShowController(@RequestBody AddShowRequest addShowRequest) {
        String msg="";
        try {
            msg = showService.addNewShow(addShowRequest);
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(msg);
        }
    }

    @PostMapping("/addShowSeat")
    public ResponseEntity<String> requestAddSeatsController(@RequestBody AddShowSeatRequest addShowSeatRequest) {
        String msg="";
        try {
            msg = showService.addShowSeatsInTheater(addShowSeatRequest);
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Bad request!");
        }
    }
    
}

package com.bookedNbusy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookedNbusy.RequestDTO.AddTheaterRequest;
import com.bookedNbusy.RequestDTO.AddTheaterSeatRequest;
import com.bookedNbusy.Service.TheaterService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/app/theater")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @PostMapping("/addNewTheater")
    public ResponseEntity<String> requestAddTheaterController(@RequestBody AddTheaterRequest addTheaterRequest) {
        String msg="";
        try {
            msg = theaterService.addNewTheater(addTheaterRequest);
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Invalid request! "+msg);
        }
    }

    @PostMapping("/addSeatsInTheater")
    public ResponseEntity<String> requestAddSeatsController(@RequestBody AddTheaterSeatRequest addTheaterSeatRequest) {
        try {
            String msg = theaterService.addSeatsInTheater(addTheaterSeatRequest);
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Bad request! "+e.getMessage());
        }
    }
}

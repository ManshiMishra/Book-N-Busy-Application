package com.bookedNbusy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookedNbusy.RequestDTO.AddMovieRequest;
import com.bookedNbusy.Service.MovieService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/app/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;
    
    @PostMapping("/addNewMovie")
    public ResponseEntity<String> requestAddMovieController(@RequestBody AddMovieRequest addMovieRequest){
        try {
            String msg = movieService.addNewMovie(addMovieRequest);
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Bad request! "+e.getMessage());
        }
    }
}

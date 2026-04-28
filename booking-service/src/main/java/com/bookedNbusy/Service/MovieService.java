package com.bookedNbusy.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookedNbusy.Entity.Movie;
import com.bookedNbusy.Repository.MovieRepository;
import com.bookedNbusy.RequestDTO.AddMovieRequest;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public String addNewMovie(AddMovieRequest addMovieRequest){
        Movie newMovie=Movie.builder().movieName(addMovieRequest.getMovieName())
        .casts(addMovieRequest.getCast())
        .movieLanguage(addMovieRequest.getMovieLanguage())
        .duration(addMovieRequest.getDuration())
        .genre(addMovieRequest.getGenre())
        .releaseDate(addMovieRequest.getReleaseDate())
        .rating(addMovieRequest.getRating())
        .build();
        newMovie = movieRepository.save(newMovie);
        return "movie "+newMovie.getMovieName()+" added successfully! with Id: "+newMovie.getMovieId();
    }
}

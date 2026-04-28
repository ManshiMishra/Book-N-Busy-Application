package com.bookedNbusy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookedNbusy.Entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {

    Movie findByMovieName(String movieName);
}

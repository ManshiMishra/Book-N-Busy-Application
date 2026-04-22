package com.bookedNbusy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookedNbusy.Entity.TheaterSeat;

public interface TheaterSeatRepository extends JpaRepository<TheaterSeat, Integer> {
    
}

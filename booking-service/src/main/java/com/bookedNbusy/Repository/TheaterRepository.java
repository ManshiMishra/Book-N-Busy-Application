package com.bookedNbusy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookedNbusy.Entity.Theater;

@Repository
public interface TheaterRepository extends JpaRepository<Theater,Integer>{

}

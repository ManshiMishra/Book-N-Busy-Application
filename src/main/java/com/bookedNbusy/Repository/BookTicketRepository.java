package com.bookedNbusy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookedNbusy.Entity.Ticket;

public interface BookTicketRepository extends JpaRepository<Ticket,String>{
    
}

package com.bookedNbusy.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookedNbusy.CustomException.InvalidShowException;
import com.bookedNbusy.Entity.Show;
import com.bookedNbusy.Entity.ShowSeat;
import com.bookedNbusy.Entity.Ticket;
import com.bookedNbusy.Repository.BookTicketRepository;
import com.bookedNbusy.Repository.ShowRepository;
import com.bookedNbusy.RequestDTO.AddBookTicketRequest;

@Service
public class BookTicketService {
     @Autowired
     private BookTicketRepository bookTicketRepository;

     @Autowired
     private ShowRepository showRepository;

     public String bookTicketWithShowId(AddBookTicketRequest addBookTicketRequest) throws Exception {

          Show showEntity = showRepository.findById(addBookTicketRequest.getShowId())
                    .orElseThrow(() -> new InvalidShowException(
                              "No show with show id " + addBookTicketRequest.getShowId() + " found!"));
          List<ShowSeat> showSeatList = showEntity.getShowSeatList();
          Integer totalTicketPrice = 0;
          for (String seatToBeBooked : addBookTicketRequest.getSelectedSeatList()) {
               for (ShowSeat showSeat : showSeatList) {
                    if (seatToBeBooked.equalsIgnoreCase(showSeat.getSeatNumber())) {
                         if (showSeat.isAvailability()) {
                              totalTicketPrice = totalTicketPrice + showSeat.getPrice();
                              showSeat.setAvailability(false);
                         } else {
                              throw new RuntimeException(
                                        "Show seat is not available for seat number " + showSeat.getSeatNumber());
                         }
                    }
               }
          }
          Ticket ticket = Ticket.builder()
                    .bookedSeat(addBookTicketRequest.getSelectedSeatList().toString())
                    .totalAmountPaid(totalTicketPrice)
                    .show(showEntity)
                    .build();
          showEntity.getTicket().add(ticket);
          showRepository.save(showEntity);
          return "Successfully booked ticket " + addBookTicketRequest.getSelectedSeatList().toString() + " for movie "
                    + showEntity.getMovie().getMovieName();

     }
}

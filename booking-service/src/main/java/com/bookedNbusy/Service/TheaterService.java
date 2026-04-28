package com.bookedNbusy.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookedNbusy.Entity.Theater;
import com.bookedNbusy.Entity.TheaterSeat;
import com.bookedNbusy.Enums.SeatType;
import com.bookedNbusy.Repository.TheaterRepository;
import com.bookedNbusy.RequestDTO.AddTheaterRequest;
import com.bookedNbusy.RequestDTO.AddTheaterSeatRequest;

@Service
public class TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;

    public String addNewTheater(AddTheaterRequest addTheaterRequest) {

        Theater newtTheater = Theater.builder()
                .theaterName(addTheaterRequest.getTheaterName())
                .address(addTheaterRequest.getAddress())
                .noOfScreens(addTheaterRequest.getNoOfScreens())
                .screenType(addTheaterRequest.getScreenType())
                .build();

        newtTheater = theaterRepository.save(newtTheater);
        return "movie " + newtTheater.getTheaterName() + " added successfully! with Id: " + newtTheater.getTheaterId();
    }

    public String addSeatsInTheater(AddTheaterSeatRequest addTheaterSeatRequest) {
        int classic = addTheaterSeatRequest.getNumberOfClassicSeats();
        int premium = addTheaterSeatRequest.getNumberOfPremiumSeats();
        int sofa = addTheaterSeatRequest.getNuumberOfSofaSeats();
        int n = addTheaterSeatRequest.getMaxSeatInARow();
        Theater theater=theaterRepository.findById(addTheaterSeatRequest.getTheaterId()).get();

        int qc = classic / n;
        int rc = classic % n;
        int qp = premium / n;
        int rp = premium % n;
        int qs = sofa / n;
        int rs = sofa % n;

        List<TheaterSeat> theaterSeatList=new ArrayList<>();

        // arrangement for classic
        for (int row = 1; row <= qc; row++) {
            for (int col = 1; col <= n; col++) {
                char ch = (char) ('A' + (row - 1));
                String seat = "C" + ch + col;
                TheaterSeat theaterSeat = TheaterSeat.builder()
                        .SeatNumber(seat)
                        .seatType(SeatType.CLASSIC)
                        .theater(theater)
                        .build();
                theaterSeatList.add(theaterSeat);
            }
        }
        int rowReminder = qc + 1;
        for (int col = 1; col <= rc; col++) {
            char ch = (char) ('A' + (rowReminder - 1));
            String seat = "C" + ch + col;
            TheaterSeat theaterSeat = TheaterSeat.builder()
                    .SeatNumber(seat)
                    .seatType(SeatType.CLASSIC)
                    .theater(theater)
                    .build();
            theaterSeatList.add(theaterSeat);
        }

        // arrangement for premium
        for (int row = 1; row <= qp; row++) {
            for (int col = 1; col <= n; col++) {
                char ch = (char) ('A' + (row - 1));
                String seat = "P" + ch + col;
                TheaterSeat theaterSeat = TheaterSeat.builder()
                        .SeatNumber(seat)
                        .seatType(SeatType.PREMIUM)
                        .theater(theater)
                        .build();
                theaterSeatList.add(theaterSeat);
            }
        }
        rowReminder = qp + 1;
        for (int col = 1; col <= rp; col++) {
            char ch = (char) ('A' + (rowReminder - 1));
            String seat = "P" + ch + col;
            TheaterSeat theaterSeat = TheaterSeat.builder()
                    .SeatNumber(seat)
                    .seatType(SeatType.PREMIUM)
                    .theater(theater)
                    .build();
            theaterSeatList.add(theaterSeat);
        }

        // arrangement for sofa
        for (int row = 1; row <= qs; row++) {
            for (int col = 1; col <= n; col++) {
                char ch = (char) ('A' + (row - 1));
                String seat = "S" + ch + col;
                TheaterSeat theaterSeat = TheaterSeat.builder()
                        .SeatNumber(seat)
                        .seatType(SeatType.SOFA)
                        .theater(theater)
                        .build();
                theaterSeatList.add(theaterSeat);
            }
        }
        rowReminder = qs + 1;
        for (int col = 1; col <= rs; col++) {
            char ch = (char) ('A' + (rowReminder - 1));
            String seat = "S" + ch + col;
            TheaterSeat theaterSeat = TheaterSeat.builder()
                    .SeatNumber(seat)
                    .seatType(SeatType.SOFA)
                    .theater(theater)
                    .build();
            theaterSeatList.add(theaterSeat);
        }

        theater.setSeatList(theaterSeatList);
        theaterRepository.save(theater);

        return "Seats are add in theater!";

    }
}

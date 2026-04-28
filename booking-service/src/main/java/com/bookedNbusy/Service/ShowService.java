package com.bookedNbusy.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookedNbusy.CustomException.InvalidShowException;
import com.bookedNbusy.CustomException.InvalidMovieName;
import com.bookedNbusy.CustomException.InvalidTheaterId;
import com.bookedNbusy.Entity.Movie;
import com.bookedNbusy.Entity.Show;
import com.bookedNbusy.Entity.ShowSeat;
import com.bookedNbusy.Entity.Theater;
import com.bookedNbusy.Entity.TheaterSeat;
import com.bookedNbusy.Enums.SeatType;
import com.bookedNbusy.Repository.MovieRepository;
import com.bookedNbusy.Repository.ShowRepository;
import com.bookedNbusy.Repository.TheaterRepository;
import com.bookedNbusy.RequestDTO.AddShowRequest;
import com.bookedNbusy.RequestDTO.AddShowSeatRequest;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    public String addNewShow(AddShowRequest addShowRequest) throws Exception {

        Movie movie = movieRepository.findByMovieName(addShowRequest.getMovieName());
        if (movie == null) {
            throw new InvalidMovieName("Movie with " + addShowRequest.getMovieName() + " not found!");
        }
        Theater theater = theaterRepository.findById(addShowRequest.getTheaterId()).get();
        if (theater == null) {
            throw new InvalidTheaterId("No theater find with " + addShowRequest.getTheaterId());
        }
        Show showEntity = new Show(addShowRequest.getShowDate(), addShowRequest.getShowTime());
        showEntity.setMovie(movie);
        showEntity.setTheaterShow(theater);

        movie.getShowList().add(showEntity);
        theater.getShowList().add(showEntity);

        showEntity = showRepository.save(showEntity);

        return "New Show added for Movie " + addShowRequest.getMovieName() + " with show id" + showEntity.getShowId();
    }

    public String addShowSeatsInTheater(AddShowSeatRequest addShowSeatRequest) throws Exception {
        Show showEntity = showRepository.findById(addShowSeatRequest.getShowId())
                .orElseThrow(() -> new InvalidShowException(
                        "No show with show id " + addShowSeatRequest.getShowId() + " found!"));

        if (!showEntity.getShowSeatList().isEmpty()) {
            throw new RuntimeException("Seats already added!");
        }
        Theater theater = showEntity.getTheaterShow();
        if (theater == null) {
            throw new InvalidTheaterId("No theater found in show, with show Id " + showEntity.getShowId());
        }
        List<TheaterSeat> theaterSeatlist = theater.getSeatList();
        List<ShowSeat> showSeatlist = new ArrayList<>();

        for (TheaterSeat theaterSeat : theaterSeatlist) {
            String seatNo = theaterSeat.getSeatNumber();
            SeatType seatType = theaterSeat.getSeatType();
            ShowSeat showSeat = ShowSeat.builder()
                    .availability(true)
                    .foodNBeverage(false)
                    .seatType(seatType)
                    .seatNumber(seatNo)
                    .show(showEntity)
                    .build();

            if (seatType.equals(SeatType.CLASSIC)) {
                showSeat.setPrice(addShowSeatRequest.getPriceClassic());
            } else if (seatType.equals(SeatType.PREMIUM)) {
                showSeat.setPrice(addShowSeatRequest.getPricePremium());
            } else if (seatType.equals(SeatType.SOFA)) {
                showSeat.setPrice(addShowSeatRequest.getPriceSofa());
            }
            showSeatlist.add(showSeat);
        }
        showEntity.setShowSeatList(showSeatlist);

        showRepository.save(showEntity);

        return "Show seats added successfully!";
    }

}

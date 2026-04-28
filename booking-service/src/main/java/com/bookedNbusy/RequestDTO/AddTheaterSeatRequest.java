package com.bookedNbusy.RequestDTO;
import lombok.Data;

@Data
public class AddTheaterSeatRequest {
   
    private int numberOfClassicSeats;

    private int numberOfPremiumSeats;

    private int nuumberOfSofaSeats;

    private int maxSeatInARow;

    private Integer theaterId;
}

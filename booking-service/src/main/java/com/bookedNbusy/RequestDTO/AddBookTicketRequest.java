package com.bookedNbusy.RequestDTO;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class AddBookTicketRequest {
    String userPhoneNumber;

    Integer showId;
    
    List<String> selectedSeatList=new ArrayList<>();
}

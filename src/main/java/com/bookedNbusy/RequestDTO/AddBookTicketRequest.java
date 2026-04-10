package com.bookedNbusy.RequestDTO;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class AddBookTicketRequest {
    Integer showId;
    List<String> selectedSeatList=new ArrayList<>();
}

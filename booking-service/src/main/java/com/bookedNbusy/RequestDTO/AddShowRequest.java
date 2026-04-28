package com.bookedNbusy.RequestDTO;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class AddShowRequest {

    private LocalDate showDate;

    private LocalTime showTime;

    private String movieName;

    private Integer theaterId;
}

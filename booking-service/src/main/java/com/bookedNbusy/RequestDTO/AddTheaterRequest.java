package com.bookedNbusy.RequestDTO;
import com.bookedNbusy.Enums.ScreenType;

import lombok.Data;

@Data
public class AddTheaterRequest {
    
    private String theaterName;

    private String address;

    private Integer noOfScreens;

    private ScreenType screenType;
}

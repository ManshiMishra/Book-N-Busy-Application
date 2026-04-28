package com.bookedNbusy.RequestDTO;

import lombok.Data;

@Data
public class AddShowSeatRequest {

    private Integer showId;

    private int priceClassic;

    private int pricePremium;

    private int priceSofa;

}

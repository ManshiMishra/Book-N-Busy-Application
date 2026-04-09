package com.bookedNbusy.CustomException;

public class InvalidTheaterId extends RuntimeException {
    public InvalidTheaterId(String msg){
        super(msg);
    }
}

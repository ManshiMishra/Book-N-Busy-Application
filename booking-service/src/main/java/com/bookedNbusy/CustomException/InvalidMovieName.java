package com.bookedNbusy.CustomException;

public class InvalidMovieName extends RuntimeException {
    public InvalidMovieName(String msg){
        super(msg);
    }
}

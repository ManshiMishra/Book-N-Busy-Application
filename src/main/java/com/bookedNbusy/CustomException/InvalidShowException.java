package com.bookedNbusy.CustomException;

public class InvalidShowException extends RuntimeException {

    public InvalidShowException(String msg){
        super(msg);
    }
}

package com.bookedNbusy.CustomException;

public class InvalidUserException extends RuntimeException{
    
    public InvalidUserException(String msg){
        super(msg);
    }
}

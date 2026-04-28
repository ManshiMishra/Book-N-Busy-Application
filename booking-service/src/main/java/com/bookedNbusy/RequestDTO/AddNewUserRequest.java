package com.bookedNbusy.RequestDTO;
import lombok.Data;

@Data
public class AddNewUserRequest {
    
    private String userName;

    private String phoneNumber;

    private String userEmail;
}

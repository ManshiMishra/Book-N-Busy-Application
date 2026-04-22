package com.bookedNbusy.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Builder
@AllArgsConstructor
@Data
public class GetTicketResponse {
    private String movieName;
    private String showTime;
    private String theaterInfo;
    private String seatDetail;
    private boolean food;
    private Integer amount;

}

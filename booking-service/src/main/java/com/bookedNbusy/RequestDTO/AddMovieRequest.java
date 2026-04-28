package com.bookedNbusy.RequestDTO;

import java.time.LocalDate;
import java.util.List;

import com.bookedNbusy.Enums.Genre;
import com.bookedNbusy.Enums.Language;

import lombok.Data;

@Data
public class AddMovieRequest {

    private String movieName;

    private LocalDate releaseDate;

    private List<String> cast;

    private Genre genre;

    private Language movieLanguage;

    private Double duration;

    private Double rating;
}

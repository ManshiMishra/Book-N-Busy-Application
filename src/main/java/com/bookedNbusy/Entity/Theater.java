package com.bookedNbusy.Entity;

import java.util.ArrayList;
import java.util.List;

import com.bookedNbusy.Enums.ScreenType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "theaters")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theater {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theaterId;

    @Column(unique = true)
    private String theaterName;

    @Column(length = 255)
    private String address;

    private Integer noOfScreens;

    @Enumerated(EnumType.STRING)
    private ScreenType screenType;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<TheaterSeat> seatList = new ArrayList<>();

    @OneToMany(mappedBy = "theaterShow", cascade = CascadeType.ALL)
    private List<Show> showList = new ArrayList<>();

}

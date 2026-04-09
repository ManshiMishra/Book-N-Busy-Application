package com.bookedNbusy.Enums;

public enum ScreenType {

    TWO_D("2D"),
    FOUR_DX("4DX"),
    DOLBY_2D("Dolby 2D"),
    MX4D("MX4D"),
    IMAX_2D("IMAX 2D"),
    ICE("ICE");

    private final String displayName;

    ScreenType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
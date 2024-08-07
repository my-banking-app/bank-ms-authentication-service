package com.mybankingapp.authenticationservice.enums;

public enum Gender {
    MALE("Hombre"),
    FEMALE("Mujer"),
    UNDISCLOSED("Prefiero no decirlo");

    private final String label;

    Gender(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

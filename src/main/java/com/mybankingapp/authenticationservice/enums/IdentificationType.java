package com.mybankingapp.authenticationservice.enums;


public enum IdentificationType {
    CC("Cédula de Ciudadanía"),
    CE("Cédula de Extranjería"),

    PA("Pasaporte");

    private final String description;

    IdentificationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

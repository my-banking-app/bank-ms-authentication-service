package com.mybankingapp.authenticationservice.enums;

/**
 * Enumeration representing different types of identification.
 */
public enum IdentificationType {
    /**
     * Cédula de Ciudadanía (Citizen ID).
     */
    CC("Cédula de Ciudadanía"),

    /**
     * Cédula de Extranjería (Foreigner ID).
     */
    CE("Cédula de Extranjería"),

    /**
     * Pasaporte (Passport).
     */
    PA("Pasaporte");

    private final String description;

    /**
     * Constructor for the IdentificationType enum.
     *
     * @param description the description of the identification type.
     */
    IdentificationType(String description) {
        this.description = description;
    }

    /**
     * Gets the description of the identification type.
     *
     * @return the description of the identification type.
     */
    public String getDescription() {
        return description;
    }
}
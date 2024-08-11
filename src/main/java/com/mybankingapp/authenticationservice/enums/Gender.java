package com.mybankingapp.authenticationservice.enums;

/**
 * Enumeration representing the gender of a user.
 */
public enum Gender {
    /**
     * Male gender.
     */
    MALE("Hombre"),

    /**
     * Female gender.
     */
    FEMALE("Mujer"),

    /**
     * Undisclosed gender.
     */
    UNDISCLOSED("Prefiero no decirlo");

    private final String label;

    /**
     * Constructor for the Gender enum.
     *
     * @param label the label representing the gender.
     */
    Gender(String label) {
        this.label = label;
    }

    /**
     * Gets the label of the gender.
     *
     * @return the label of the gender.
     */
    public String getLabel() {
        return label;
    }
}
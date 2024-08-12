package com.mybankingapp.authenticationservice.dto;

import com.mybankingapp.authenticationservice.enums.IdentificationType;

/**
 * Data Transfer Object (DTO) for login requests.
 * Contains the identification type, identification number, and password.
 */
public class LoginRequest {
    private IdentificationType identificationType;
    private String identificationNumber;
    private String password;

    /**
     * Gets the identification type.
     *
     * @return the identification type
     */
    public IdentificationType getIdentificationType() {
        return identificationType;
    }

    /**
     * Sets the identification type.
     *
     * @param identificationType the identification type to set
     */
    public void setIdentificationType(IdentificationType identificationType) {
        this.identificationType = identificationType;
    }

    /**
     * Gets the identification number.
     *
     * @return the identification number
     */
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    /**
     * Sets the identification number.
     *
     * @param identificationNumber the identification number to set
     */
    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
package com.mybankingapp.authenticationservice.dto;

import com.mybankingapp.authenticationservice.enums.IdentificationType;

public class LoginRequest {
    private IdentificationType identificationType;
    private String identificationNumber;
    private String password;

    public IdentificationType getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(IdentificationType identificationType) {
        this.identificationType = identificationType;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

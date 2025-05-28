package com.mybankingapp.authenticationservice.dto;

import com.mybankingapp.authenticationservice.enums.IdentificationType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    @Test
    void getIdentificationType_returnsCorrectType() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setIdentificationType(IdentificationType.PA);
        assertEquals(IdentificationType.PA, loginRequest.getIdentificationType());
    }

    @Test
    void setIdentificationType_setsCorrectType() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setIdentificationType(IdentificationType.CE);
        assertEquals(IdentificationType.CE, loginRequest.getIdentificationType());
    }

    @Test
    void getIdentificationNumber_returnsCorrectNumber() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setIdentificationNumber("123456789");
        assertEquals("123456789", loginRequest.getIdentificationNumber());
    }

    @Test
    void setIdentificationNumber_setsCorrectNumber() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setIdentificationNumber("987654321");
        assertEquals("987654321", loginRequest.getIdentificationNumber());
    }

    @Test
    void getPassword_returnsCorrectPassword() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("password123");
        assertEquals("password123", loginRequest.getPassword());
    }

    @Test
    void setPassword_setsCorrectPassword() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("securePassword");
        assertEquals("securePassword", loginRequest.getPassword());
    }

    @Test
    void setIdentificationType_nullType() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setIdentificationType(null);
        assertNull(loginRequest.getIdentificationType());
    }

    @Test
    void setIdentificationNumber_emptyString() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setIdentificationNumber("");
        assertEquals("", loginRequest.getIdentificationNumber());
    }

    @Test
    void setPassword_emptyString() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("");
        assertEquals("", loginRequest.getPassword());
    }
}
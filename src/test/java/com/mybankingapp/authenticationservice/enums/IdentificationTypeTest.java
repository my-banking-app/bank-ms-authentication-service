package com.mybankingapp.authenticationservice.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IdentificationTypeTest {

    @Test
    void getDescriptionReturnsCorrectDescriptionForCC() {
        assertEquals("Cédula de Ciudadanía", IdentificationType.CC.getDescription());
    }

    @Test
    void getDescriptionReturnsCorrectDescriptionForCE() {
        assertEquals("Cédula de Extranjería", IdentificationType.CE.getDescription());
    }

    @Test
    void getDescriptionReturnsCorrectDescriptionForPA() {
        assertEquals("Pasaporte", IdentificationType.PA.getDescription());
    }
}
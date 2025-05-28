package com.mybankingapp.authenticationservice.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GenderTest {

    @Test
    void getLabelReturnsCorrectLabelForMale() {
        assertEquals("Hombre", Gender.MALE.getLabel());
    }

    @Test
    void getLabelReturnsCorrectLabelForFemale() {
        assertEquals("Mujer", Gender.FEMALE.getLabel());
    }

    @Test
    void getLabelReturnsCorrectLabelForUndisclosed() {
        assertEquals("Prefiero no decirlo", Gender.UNDISCLOSED.getLabel());
    }
}
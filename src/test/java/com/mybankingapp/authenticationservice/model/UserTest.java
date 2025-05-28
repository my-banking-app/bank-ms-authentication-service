package com.mybankingapp.authenticationservice.model;

import com.mybankingapp.authenticationservice.enums.Gender;
import com.mybankingapp.authenticationservice.enums.IdentificationType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

public class UserTest {

    @Test
    void userShouldHaveValidId() {
        User user = new User();
        user.setId(UUID.randomUUID());
        assertNotNull(user.getId());
    }

    @Test
    void userShouldHaveValidIdentificationType() {
        User user = new User();
        user.setIdentificationType(IdentificationType.PA);
        assertEquals(IdentificationType.PA, user.getIdentificationType());
    }

    @Test
    void userShouldHaveValidIdentificationNumber() {
        User user = new User();
        user.setIdentificationNumber("123456789");
        assertEquals("123456789", user.getIdentificationNumber());
    }

    @Test
    void userShouldHaveValidFirstName() {
        User user = new User();
        user.setFirstName("John");
        assertEquals("John", user.getFirstName());
    }

    @Test
    void userShouldHaveValidLastName() {
        User user = new User();
        user.setLastName("Doe");
        assertEquals("Doe", user.getLastName());
    }

    @Test
    void userShouldHaveValidAge() {
        User user = new User();
        user.setAge(25);
        assertEquals(25, user.getAge());
    }

    @Test
    void userShouldHaveValidCityOfResidence() {
        User user = new User();
        user.setCityOfResidence("New York");
        assertEquals("New York", user.getCityOfResidence());
    }

    @Test
    void userShouldHaveValidNationality() {
        User user = new User();
        user.setNationality("American");
        assertEquals("American", user.getNationality());
    }

    @Test
    void userShouldHaveValidPhoneNumber() {
        User user = new User();
        user.setPhoneNumber("123-456-7890");
        assertEquals("123-456-7890", user.getPhoneNumber());
    }

    @Test
    void userShouldHaveValidCivilStatus() {
        User user = new User();
        user.setCivilStatus("Single");
        assertEquals("Single", user.getCivilStatus());
    }

    @Test
    void userShouldHaveValidEmail() {
        User user = new User();
        user.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", user.getEmail());
    }

    @Test
    void userShouldHaveValidPassword() {
        User user = new User();
        user.setPassword("Password@123");
        assertEquals("Password@123", user.getPassword());
    }

    @Test
    void userShouldHaveValidGender() {
        User user = new User();
        user.setGender(Gender.MALE);
        assertEquals(Gender.MALE, user.getGender());
    }

    @Test
    void userShouldHaveValidDataProcessingAgreement() {
        User user = new User();
        user.setDataProcessingAgreement(true);
        assertTrue(user.isDataProcessingAgreement());
    }
}
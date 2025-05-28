package com.mybankingapp.authenticationservice.dto;

import com.mybankingapp.authenticationservice.enums.IdentificationType;
import com.mybankingapp.authenticationservice.enums.Gender;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {

    @Test
    void getAndSetIdentificationType() {
        UserDto userDto = new UserDto();
        userDto.setIdentificationType(IdentificationType.PA);
        assertEquals(IdentificationType.PA, userDto.getIdentificationType());
    }

    @Test
    void getAndSetIdentificationNumber() {
        UserDto userDto = new UserDto();
        userDto.setIdentificationNumber("123456789");
        assertEquals("123456789", userDto.getIdentificationNumber());
    }

    @Test
    void getAndSetFirstName() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("John");
        assertEquals("John", userDto.getFirstName());
    }

    @Test
    void getAndSetLastName() {
        UserDto userDto = new UserDto();
        userDto.setLastName("Doe");
        assertEquals("Doe", userDto.getLastName());
    }

    @Test
    void getAndSetAge() {
        UserDto userDto = new UserDto();
        userDto.setAge(30);
        assertEquals(30, userDto.getAge());
    }

    @Test
    void getAndSetCityOfResidence() {
        UserDto userDto = new UserDto();
        userDto.setCityOfResidence("New York");
        assertEquals("New York", userDto.getCityOfResidence());
    }

    @Test
    void getAndSetNationality() {
        UserDto userDto = new UserDto();
        userDto.setNationality("American");
        assertEquals("American", userDto.getNationality());
    }

    @Test
    void getAndSetPhoneNumber() {
        UserDto userDto = new UserDto();
        userDto.setPhoneNumber("123-456-7890");
        assertEquals("123-456-7890", userDto.getPhoneNumber());
    }

    @Test
    void getAndSetCivilStatus() {
        UserDto userDto = new UserDto();
        userDto.setCivilStatus("Single");
        assertEquals("Single", userDto.getCivilStatus());
    }

    @Test
    void getAndSetEmail() {
        UserDto userDto = new UserDto();
        userDto.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", userDto.getEmail());
    }

    @Test
    void getAndSetPassword() {
        UserDto userDto = new UserDto();
        userDto.setPassword("password123");
        assertEquals("password123", userDto.getPassword());
    }

    @Test
    void getAndSetGender() {
        UserDto userDto = new UserDto();
        userDto.setGender(Gender.MALE);
        assertEquals(Gender.MALE, userDto.getGender());
    }

    @Test
    void getAndSetDataProcessingAgreement() {
        UserDto userDto = new UserDto();
        userDto.setDataProcessingAgreement(true);
        assertTrue(userDto.isDataProcessingAgreement());
    }
}
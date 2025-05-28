package com.mybankingapp.authenticationservice.dto;

import com.mybankingapp.authenticationservice.enums.Gender;
import com.mybankingapp.authenticationservice.enums.IdentificationType;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserResponseDtoTest {

    @Test
    void getId_returnsCorrectId() {
        UUID id = UUID.randomUUID();
        UserResponseDto dto = new UserResponseDto();
        dto.setId(id);
        assertEquals(id, dto.getId());
    }

    @Test
    void getIdentificationType_returnsCorrectType() {
        IdentificationType type = IdentificationType.PA;
        UserResponseDto dto = new UserResponseDto();
        dto.setIdentificationType(type);
        assertEquals(type, dto.getIdentificationType());
    }

    @Test
    void getIdentificationNumber_returnsCorrectNumber() {
        String number = "123456789";
        UserResponseDto dto = new UserResponseDto();
        dto.setIdentificationNumber(number);
        assertEquals(number, dto.getIdentificationNumber());
    }

    @Test
    void getFirstName_returnsCorrectFirstName() {
        String firstName = "John";
        UserResponseDto dto = new UserResponseDto();
        dto.setFirstName(firstName);
        assertEquals(firstName, dto.getFirstName());
    }

    @Test
    void getLastName_returnsCorrectLastName() {
        String lastName = "Doe";
        UserResponseDto dto = new UserResponseDto();
        dto.setLastName(lastName);
        assertEquals(lastName, dto.getLastName());
    }

    @Test
    void getAge_returnsCorrectAge() {
        Integer age = 30;
        UserResponseDto dto = new UserResponseDto();
        dto.setAge(age);
        assertEquals(age, dto.getAge());
    }

    @Test
    void getCityOfResidence_returnsCorrectCity() {
        String city = "New York";
        UserResponseDto dto = new UserResponseDto();
        dto.setCityOfResidence(city);
        assertEquals(city, dto.getCityOfResidence());
    }

    @Test
    void getNationality_returnsCorrectNationality() {
        String nationality = "American";
        UserResponseDto dto = new UserResponseDto();
        dto.setNationality(nationality);
        assertEquals(nationality, dto.getNationality());
    }

    @Test
    void getPhoneNumber_returnsCorrectPhoneNumber() {
        String phoneNumber = "123-456-7890";
        UserResponseDto dto = new UserResponseDto();
        dto.setPhoneNumber(phoneNumber);
        assertEquals(phoneNumber, dto.getPhoneNumber());
    }

    @Test
    void getCivilStatus_returnsCorrectCivilStatus() {
        String civilStatus = "Single";
        UserResponseDto dto = new UserResponseDto();
        dto.setCivilStatus(civilStatus);
        assertEquals(civilStatus, dto.getCivilStatus());
    }

    @Test
    void getEmail_returnsCorrectEmail() {
        String email = "john.doe@example.com";
        UserResponseDto dto = new UserResponseDto();
        dto.setEmail(email);
        assertEquals(email, dto.getEmail());
    }

    @Test
    void getGender_returnsCorrectGender() {
        Gender gender = Gender.MALE;
        UserResponseDto dto = new UserResponseDto();
        dto.setGender(gender);
        assertEquals(gender, dto.getGender());
    }

    @Test
    void getToken_returnsCorrectToken() {
        String token = "token123";
        UserResponseDto dto = new UserResponseDto();
        dto.setToken(token);
        assertEquals(token, dto.getToken());
    }

    @Test
    void defaultValues_areNull() {
        UserResponseDto dto = new UserResponseDto();
        assertNull(dto.getId());
        assertNull(dto.getIdentificationType());
        assertNull(dto.getIdentificationNumber());
        assertNull(dto.getFirstName());
        assertNull(dto.getLastName());
        assertNull(dto.getAge());
        assertNull(dto.getCityOfResidence());
        assertNull(dto.getNationality());
        assertNull(dto.getPhoneNumber());
        assertNull(dto.getCivilStatus());
        assertNull(dto.getEmail());
        assertNull(dto.getGender());
        assertNull(dto.getToken());
    }
}
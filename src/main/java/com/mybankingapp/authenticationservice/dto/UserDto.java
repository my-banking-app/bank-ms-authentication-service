package com.mybankingapp.authenticationservice.dto;

import com.mybankingapp.authenticationservice.enums.IdentificationType;
import com.mybankingapp.authenticationservice.enums.Gender;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for user information.
 * Contains various fields related to user details.
 */
@Data
public class UserDto {
    private IdentificationType identificationType;
    private String identificationNumber;
    private String firstName;
    private String lastName;
    private int age;
    private String cityOfResidence;
    private String nationality;
    private String phoneNumber;
    private String civilStatus;
    private String email;
    private String password;
    private Gender gender;
    private boolean dataProcessingAgreement;

}
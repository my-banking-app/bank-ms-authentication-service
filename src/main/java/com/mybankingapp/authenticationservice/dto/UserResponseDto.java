package com.mybankingapp.authenticationservice.dto;

import com.mybankingapp.authenticationservice.enums.Gender;
import com.mybankingapp.authenticationservice.enums.IdentificationType;

import java.util.UUID;

/**
 * Data Transfer Object for User Response.
 */
public class UserResponseDto {
    private UUID id;
    private IdentificationType identificationType;
    private String identificationNumber;
    private String firstName;
    private String lastName;
    private Integer age;
    private String cityOfResidence;
    private String nationality;
    private String phoneNumber;
    private String civilStatus;
    private String email;
    private Gender gender;
    private String token;

    /**
     * Gets the user ID.
     * @return the user ID.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the user ID.
     * @param id the user ID.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the identification type.
     * @return the identification type.
     */
    public IdentificationType getIdentificationType() {
        return identificationType;
    }

    /**
     * Sets the identification type.
     * @param identificationType the identification type.
     */
    public void setIdentificationType(IdentificationType identificationType) {
        this.identificationType = identificationType;
    }

    /**
     * Gets the identification number.
     * @return the identification number.
     */
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    /**
     * Sets the identification number.
     * @param identificationNumber the identification number.
     */
    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    /**
     * Gets the first name.
     * @return the first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     * @param firstName the first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name.
     * @return the last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     * @param lastName the last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the age.
     * @return the age.
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Sets the age.
     * @param age the age.
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Gets the city of residence.
     * @return the city of residence.
     */
    public String getCityOfResidence() {
        return cityOfResidence;
    }

    /**
     * Sets the city of residence.
     * @param cityOfResidence the city of residence.
     */
    public void setCityOfResidence(String cityOfResidence) {
        this.cityOfResidence = cityOfResidence;
    }

    /**
     * Gets the nationality.
     * @return the nationality.
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Sets the nationality.
     * @param nationality the nationality.
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * Gets the phone number.
     * @return the phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number.
     * @param phoneNumber the phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the civil status.
     * @return the civil status.
     */
    public String getCivilStatus() {
        return civilStatus;
    }

    /**
     * Sets the civil status.
     * @param civilStatus the civil status.
     */
    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    /**
     * Gets the email.
     * @return the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     * @param email the email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the gender.
     * @return the gender.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets the gender.
     * @param gender the gender.
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Gets the token.
     * @return the token.
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the token.
     * @param token the token.
     */
    public void setToken(String token) {
        this.token = token;
    }
}
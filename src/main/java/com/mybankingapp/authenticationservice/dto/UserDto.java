package com.mybankingapp.authenticationservice.dto;

import com.mybankingapp.authenticationservice.enums.IdentificationType;
import com.mybankingapp.authenticationservice.enums.Gender;

/**
 * Data Transfer Object (DTO) for user information.
 * Contains various fields related to user details.
 */
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

    // Getters and setters for each field

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
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age.
     *
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the city of residence.
     *
     * @return the city of residence
     */
    public String getCityOfResidence() {
        return cityOfResidence;
    }

    /**
     * Sets the city of residence.
     *
     * @param cityOfResidence the city of residence to set
     */
    public void setCityOfResidence(String cityOfResidence) {
        this.cityOfResidence = cityOfResidence;
    }

    /**
     * Gets the nationality.
     *
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Sets the nationality.
     *
     * @param nationality the nationality to set
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * Gets the phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number.
     *
     * @param phoneNumber the phone number to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the civil status.
     *
     * @return the civil status
     */
    public String getCivilStatus() {
        return civilStatus;
    }

    /**
     * Sets the civil status.
     *
     * @param civilStatus the civil status to set
     */
    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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

    /**
     * Gets the gender.
     *
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets the gender.
     *
     * @param gender the gender to set
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Checks if the user has agreed to data processing.
     *
     * @return true if the user has agreed, false otherwise
     */
    public boolean isDataProcessingAgreement() {
        return dataProcessingAgreement;
    }

    /**
     * Sets the data processing agreement status.
     *
     * @param dataProcessingAgreement the data processing agreement status to set
     */
    public void setDataProcessingAgreement(boolean dataProcessingAgreement) {
        this.dataProcessingAgreement = dataProcessingAgreement;
    }
}
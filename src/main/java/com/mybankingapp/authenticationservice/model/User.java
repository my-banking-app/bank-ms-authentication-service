package com.mybankingapp.authenticationservice.model;

import com.mybankingapp.authenticationservice.enums.Gender;
import com.mybankingapp.authenticationservice.enums.IdentificationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.UUID;

/**
 * Represents a User entity in the authentication service.
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * Unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * Type of identification for the user.
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IdentificationType identificationType;

    /**
     * Identification number of the user.
     */
    @NotBlank
    @Column(nullable = false)
    private String identificationNumber;

    /**
     * First name of the user.
     */
    @NotBlank
    @Column(nullable = false)
    private String firstName;

    /**
     * Last name of the user.
     */
    @NotBlank
    @Column(nullable = false)
    private String lastName;

    /**
     * Age of the user. Must be at least 18.
     */
    @Min(18)
    @Column(nullable = false)
    private int age;

    /**
     * City of residence of the user.
     */
    @NotBlank
    @Column(nullable = false)
    private String cityOfResidence;

    /**
     * Nationality of the user.
     */
    @NotBlank
    @Column(nullable = false)
    private String nationality;

    /**
     * Phone number of the user.
     */
    @NotBlank
    @Column(nullable = false)
    private String phoneNumber;

    /**
     * Civil status of the user.
     */
    @NotBlank
    @Column(nullable = false)
    private String civilStatus;

    /**
     * Email address of the user.
     */
    @Email
    @Column(nullable = false)
    private String email;

    /**
     * Password of the user. Must meet complexity requirements.
     */
    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    @Column(nullable = false)
    private String password;

    /**
     * Gender of the user.
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    /**
     * Indicates whether the user has agreed to data processing.
     */
    @Column(nullable = false)
    private boolean dataProcessingAgreement;

    // Getters and Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public IdentificationType getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(IdentificationType identificationType) {
        this.identificationType = identificationType;
    }

    public @NotBlank String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(@NotBlank String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public @NotBlank String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank String lastName) {
        this.lastName = lastName;
    }

    @Min(18)
    public int getAge() {
        return age;
    }

    public void setAge(@Min(18) int age) {
        this.age = age;
    }

    public @NotBlank String getCityOfResidence() {
        return cityOfResidence;
    }

    public void setCityOfResidence(@NotBlank String cityOfResidence) {
        this.cityOfResidence = cityOfResidence;
    }

    public @NotBlank String getNationality() {
        return nationality;
    }

    public void setNationality(@NotBlank String nationality) {
        this.nationality = nationality;
    }

    public @NotBlank String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @NotBlank String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(@NotBlank String civilStatus) {
        this.civilStatus = civilStatus;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public @NotBlank @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$") String password) {
        this.password = password;
    }

    public @NotNull Gender getGender() {
        return gender;
    }

    public void setGender(@NotNull Gender gender) {
        this.gender = gender;
    }

    public boolean isDataProcessingAgreement() {
        return dataProcessingAgreement;
    }

    public void setDataProcessingAgreement(boolean dataProcessingAgreement) {
        this.dataProcessingAgreement = dataProcessingAgreement;
    }
}
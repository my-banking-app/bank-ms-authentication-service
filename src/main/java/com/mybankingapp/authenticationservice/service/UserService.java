package com.mybankingapp.authenticationservice.service;

import com.mybankingapp.authenticationservice.dto.UserDto;
import com.mybankingapp.authenticationservice.dto.UserResponseDto;
import com.mybankingapp.authenticationservice.enums.IdentificationType;
import com.mybankingapp.authenticationservice.model.User;
import com.mybankingapp.authenticationservice.repository.UserRepository;
import com.mybankingapp.authenticationservice.security.utils.JwtTokenUtil;
import com.mybankingapp.authenticationservice.security.utils.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for managing user-related operations.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * Registers a new user.
     *
     * @param userDto the user data transfer object containing user details
     * @return the registered user
     */
    public User registerUser(UserDto userDto) {
        boolean exists = userRepository.existsByIdentificationTypeAndIdentificationNumber(
                userDto.getIdentificationType(), userDto.getIdentificationNumber());

        if (exists) {
            throw new IllegalArgumentException("El usuario con esta identificación ya está registrado.");
        }

        User user = new User();
        user.setIdentificationType(userDto.getIdentificationType());
        user.setIdentificationNumber(userDto.getIdentificationNumber());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAge(userDto.getAge());
        user.setCityOfResidence(userDto.getCityOfResidence());
        user.setNationality(userDto.getNationality());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setCivilStatus(userDto.getCivilStatus());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setGender(userDto.getGender());
        user.setDataProcessingAgreement(userDto.isDataProcessingAgreement());
        return userRepository.save(user);
    }


    /**
     * Authenticates a user and generates a JWT token if successful.
     *
     * @param identificationType the type of identification (e.g., ID card, passport)
     * @param identificationNumber the identification number
     * @param password the user's password
     * @return a UserResponseDto containing user details and JWT token, or null if authentication fails
     */
    public UserResponseDto login(String identificationType, String identificationNumber, String password) {
        User user = userRepository.findByIdentificationTypeAndIdentificationNumber(IdentificationType.valueOf(identificationType), identificationNumber);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            String token = jwtTokenUtil.generateToken(user.getIdentificationNumber(), user.getIdentificationType());
            UserResponseDto userDto = mapToDto(user);
            userDto.setToken(token);
            return userDto;
        }
        return null;
    }

    /**
     * Maps a User entity to a UserResponseDto.
     *
     * @param user the user entity
     * @return the user response data transfer object
     */
    private UserResponseDto mapToDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setIdentificationType(user.getIdentificationType());
        dto.setIdentificationNumber(user.getIdentificationNumber());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setAge(user.getAge());
        dto.setCityOfResidence(user.getCityOfResidence());
        dto.setNationality(user.getNationality());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setCivilStatus(user.getCivilStatus());
        dto.setEmail(user.getEmail());
        dto.setGender(user.getGender());
        return dto;
    }

    /**
     * Checks if the provided credentials are valid.
     *
     * @param identificationTypeStr the type of identification as a string
     * @param identificationNumber the identification number
     * @param password the user's password
     * @return true if the credentials are valid, false otherwise
     */
    public boolean checkCredentials(String identificationTypeStr, String identificationNumber, String password) {
        IdentificationType identificationType;
        try {
            identificationType = IdentificationType.valueOf(identificationTypeStr);
        } catch (IllegalArgumentException e) {
            return false;
        }

        User user = userRepository.findByIdentificationTypeAndIdentificationNumber(identificationType, identificationNumber);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    /**
     * Loads user details by identification type and number.
     *
     * @param identificationType the type of identification
     * @param identificationNumber the identification number
     * @return the user details, or null if the user is not found
     */
    public UserDetails loadUserByIdentificationTypeAndNumber(IdentificationType identificationType, String identificationNumber) {
        User user = userRepository.findByIdentificationTypeAndIdentificationNumber(identificationType, identificationNumber);
        return user != null ? new MyUserDetails(user) : null;
    }
}
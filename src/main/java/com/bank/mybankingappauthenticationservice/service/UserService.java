package com.bank.mybankingappauthenticationservice.service;

import com.bank.mybankingappauthenticationservice.dto.UserDto;
import com.bank.mybankingappauthenticationservice.dto.UserResponseDto;
import com.bank.mybankingappauthenticationservice.enums.IdentificationType;
import com.bank.mybankingappauthenticationservice.model.User;
import com.bank.mybankingappauthenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserDto userDto) {
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

    public UserResponseDto login(IdentificationType identificationType, String identificationNumber, String password) {
        User user = userRepository.findByIdentificationTypeAndIdentificationNumber(identificationType, identificationNumber);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return mapToDto(user);
        }
        return null;
    }

    private UserResponseDto mapToDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setIdentificationType(user.getIdentificationType().name());
        dto.setIdentificationNumber(user.getIdentificationNumber());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setAge(user.getAge());
        dto.setCityOfResidence(user.getCityOfResidence());
        dto.setNationality(user.getNationality());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setCivilStatus(user.getCivilStatus());
        dto.setEmail(user.getEmail());
        dto.setGender(user.getGender().name());
        return dto;
    }

    public boolean checkCredentials(String identificationTypeStr, String identificationNumber, String password) {
        IdentificationType identificationType;
        try {
            identificationType = IdentificationType.valueOf(identificationTypeStr);
        } catch (IllegalArgumentException e) {
            // Handle the case where the enum conversion fails
            return false;
        }

        User user = userRepository.findByIdentificationTypeAndIdentificationNumber(identificationType, identificationNumber);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }
}

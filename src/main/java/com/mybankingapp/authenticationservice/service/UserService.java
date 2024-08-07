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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

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

    public UserDetails loadUserByIdentificationTypeAndNumber(IdentificationType identificationType, String identificationNumber) {
        User user = userRepository.findByIdentificationTypeAndIdentificationNumber(identificationType, identificationNumber);
        return user != null ? new MyUserDetails(user) : null;  // MyUserDetails debe implementar UserDetails
    }
}

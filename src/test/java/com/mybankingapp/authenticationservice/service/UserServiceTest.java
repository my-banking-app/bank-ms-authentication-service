package com.mybankingapp.authenticationservice.service;

import com.mybankingapp.authenticationservice.dto.UserDto;
import com.mybankingapp.authenticationservice.dto.UserResponseDto;
import com.mybankingapp.authenticationservice.enums.Gender;
import com.mybankingapp.authenticationservice.enums.IdentificationType;
import com.mybankingapp.authenticationservice.model.User;
import com.mybankingapp.authenticationservice.repository.UserRepository;
import com.mybankingapp.authenticationservice.security.utils.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @InjectMocks
    private UserService userService;

    private UserDto userDto;
    private User user;

    @BeforeEach
    void setUp() {
        openMocks(this);

        userDto = new UserDto();
        userDto.setIdentificationType(IdentificationType.CC);
        userDto.setIdentificationNumber("123456789");
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setAge(30);
        userDto.setCityOfResidence("Bogotá");
        userDto.setNationality("Colombian");
        userDto.setPhoneNumber("3001234567");
        userDto.setCivilStatus("Single");
        userDto.setEmail("john@example.com");
        userDto.setPassword("SecureP@ss1");
        userDto.setGender(Gender.MALE);
        userDto.setDataProcessingAgreement(true);

        user = new User();
        user.setId(UUID.randomUUID());
        user.setIdentificationType(IdentificationType.CC);
        user.setIdentificationNumber("123456789");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setAge(30);
        user.setCityOfResidence("Bogotá");
        user.setNationality("Colombian");
        user.setPhoneNumber("3001234567");
        user.setCivilStatus("Single");
        user.setEmail("john@example.com");
        user.setPassword("hashedPassword");
        user.setGender(Gender.MALE);
        user.setDataProcessingAgreement(true);
    }

    @Test
    void shouldRegisterUserSuccessfully() {
        when(userRepository.existsByIdentificationTypeAndIdentificationNumber(any(), any())).thenReturn(false);
        when(passwordEncoder.encode(any())).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.registerUser(userDto);

        assertNotNull(result);
        assertEquals("123456789", result.getIdentificationNumber());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void shouldThrowWhenUserAlreadyExists() {
        when(userRepository.existsByIdentificationTypeAndIdentificationNumber(any(), any())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(userDto));
    }

    @Test
    void shouldLoginSuccessfully() {
        when(userRepository.findByIdentificationTypeAndIdentificationNumber(any(), any())).thenReturn(user);
        when(passwordEncoder.matches(any(), any())).thenReturn(true);
        when(jwtTokenUtil.generateToken(any(), any())).thenReturn("token");

        UserResponseDto response = userService.login("CC", "123456789", "SecureP@ss1");

        assertNotNull(response);
        assertEquals("token", response.getToken());
    }

    @Test
    void shouldReturnNullWhenLoginFails() {
        when(userRepository.findByIdentificationTypeAndIdentificationNumber(any(), any())).thenReturn(user);
        when(passwordEncoder.matches(any(), any())).thenReturn(false);

        UserResponseDto response = userService.login("CC", "123456789", "WrongPass");

        assertNull(response);
    }

    @Test
    void shouldValidateCorrectCredentials() {
        when(userRepository.findByIdentificationTypeAndIdentificationNumber(any(), any())).thenReturn(user);
        when(passwordEncoder.matches(any(), any())).thenReturn(true);

        assertTrue(userService.checkCredentials("CC", "123456789", "SecureP@ss1"));
    }

    @Test
    void shouldFailCredentialCheckWhenUserNotFound() {
        when(userRepository.findByIdentificationTypeAndIdentificationNumber(any(), any())).thenReturn(null);

        assertFalse(userService.checkCredentials("CC", "123456789", "SecureP@ss1"));
    }

    @Test
    void shouldLoadUserDetailsSuccessfully() {
        when(userRepository.findByIdentificationTypeAndIdentificationNumber(any(), any())).thenReturn(user);

        UserDetails details = userService.loadUserByIdentificationTypeAndNumber(IdentificationType.CC, "123456789");

        assertNotNull(details);
    }

    @Test
    void shouldReturnNullWhenLoadingUnknownUser() {
        when(userRepository.findByIdentificationTypeAndIdentificationNumber(any(), any())).thenReturn(null);

        UserDetails details = userService.loadUserByIdentificationTypeAndNumber(IdentificationType.CC, "999999");

        assertNull(details);
    }
}

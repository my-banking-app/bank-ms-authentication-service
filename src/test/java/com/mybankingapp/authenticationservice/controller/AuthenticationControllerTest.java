package com.mybankingapp.authenticationservice.controller;

import com.mybankingapp.authenticationservice.dto.LoginRequest;
import com.mybankingapp.authenticationservice.dto.UserDto;
import com.mybankingapp.authenticationservice.dto.UserResponseDto;
import com.mybankingapp.authenticationservice.model.User;
import com.mybankingapp.authenticationservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.mybankingapp.authenticationservice.enums.IdentificationType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AuthenticationControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_Success() {
        UserDto userDto = new UserDto();
        User user = new User();
        when(userService.registerUser(any(UserDto.class))).thenReturn(user);

        ResponseEntity<User> response = authenticationController.registerUser(userDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void login_Success() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setIdentificationType(IdentificationType.CC);
        loginRequest.setIdentificationNumber("123456789");
        loginRequest.setPassword("password");
        UserResponseDto userResponseDto = new UserResponseDto();
        when(userService.login(any(String.class), any(String.class), any(String.class))).thenReturn(userResponseDto);

        ResponseEntity<?> response = authenticationController.login(loginRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponseDto, response.getBody());
    }

    @Test
    void login_InvalidCredentials() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setIdentificationType(IdentificationType.CC);
        loginRequest.setIdentificationNumber("123456789");
        loginRequest.setPassword("wrongpassword");
        when(userService.login(any(String.class), any(String.class), any(String.class))).thenReturn(null);

        ResponseEntity<?> response = authenticationController.login(loginRequest);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid credentials", response.getBody());
    }
}
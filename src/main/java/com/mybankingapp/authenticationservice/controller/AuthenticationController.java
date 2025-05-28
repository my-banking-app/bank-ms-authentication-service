package com.mybankingapp.authenticationservice.controller;

import com.mybankingapp.authenticationservice.dto.LoginRequest;
import com.mybankingapp.authenticationservice.dto.UserDto;
import com.mybankingapp.authenticationservice.dto.UserResponseDto;
import com.mybankingapp.authenticationservice.model.User;
import com.mybankingapp.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

/**
 * REST controller for handling authentication-related requests.
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    /**
     * Service for handling user-related operations.
     */
    @Autowired
    private UserService userService;

    /**
     * Registers a new user.
     *
     * @param userDto the user data transfer object containing user details
     * @return a ResponseEntity containing the registered user
     */
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {
        User user = userService.registerUser(userDto);
        return ResponseEntity.ok(user);
    }

    /**
     * Authenticates a user and returns user details if successful.
     *
     * @param loginRequest the login request containing identification type, identification number, and password
     * @return a ResponseEntity containing the user details if authentication is successful, or an error message if not
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            UserResponseDto userDto = userService.login(
                    loginRequest.getIdentificationType().name(),
                    loginRequest.getIdentificationNumber(),
                    loginRequest.getPassword()
            );
            if (userDto != null) {
                return ResponseEntity.ok(userDto);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed: " + e.getMessage());
        }
    }
}
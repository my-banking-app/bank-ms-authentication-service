package com.mybankingapp.authenticationservice.controller;


import com.mybankingapp.authenticationservice.dto.LoginRequest;
import com.mybankingapp.authenticationservice.dto.UserDto;
import com.mybankingapp.authenticationservice.dto.UserResponseDto;
import com.mybankingapp.authenticationservice.model.User;
import com.mybankingapp.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {
        User user = userService.registerUser(userDto);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
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
    }

}

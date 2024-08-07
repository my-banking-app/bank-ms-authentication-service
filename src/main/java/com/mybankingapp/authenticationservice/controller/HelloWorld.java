package com.mybankingapp.authenticationservice.controller;

import com.mybankingapp.authenticationservice.dto.LoginRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {


    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}

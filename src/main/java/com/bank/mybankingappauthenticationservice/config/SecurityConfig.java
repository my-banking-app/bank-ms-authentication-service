package com.bank.mybankingappauthenticationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Desactivar CSRF para desarrollo
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/v1/auth/register", "/api/v1/auth/login").permitAll()  // Permitir todos los accesos a estos endpoints
                        .anyRequest().authenticated()  // Todos los demás endpoints requieren autenticación
                )
                .httpBasic(httpBasic -> httpBasic.disable())  // Desactivar la autenticación básica HTTP
                .formLogin(formLogin -> formLogin.disable());  // Desactivar el formulario de inicio de sesión automático

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
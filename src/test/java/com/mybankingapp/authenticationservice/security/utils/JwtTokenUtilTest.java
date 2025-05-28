package com.mybankingapp.authenticationservice.security.utils;

import com.mybankingapp.authenticationservice.enums.IdentificationType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Base64;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JwtTokenUtilTest {

    @InjectMocks
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expiration;

    @BeforeEach
    void setUp() {
        // Set the values for the secretKey and expiration using ReflectionTestUtils
        ReflectionTestUtils.setField(jwtTokenUtil, "secretKey", secretKey);
        ReflectionTestUtils.setField(jwtTokenUtil, "expiration", expiration);
    }

    @Test
    void testGenerateToken() {
        // Arrange
        String identificationNumber = "123456";
        IdentificationType identificationType = IdentificationType.CC;

        // Act
        String token = jwtTokenUtil.generateToken(identificationNumber, identificationType);

        // Assert
        assertNotNull(token);
        String decodedToken = new String(Base64.getDecoder().decode(token.split("\\.")[1]));
        assertTrue(decodedToken.contains(identificationNumber));
        assertTrue(decodedToken.contains(identificationType.name()));
    }

    @Test
    void testValidateToken_WithValidToken() {
        // Arrange
        String identificationNumber = "123456";
        IdentificationType identificationType = IdentificationType.CC;
        String token = jwtTokenUtil.generateToken(identificationNumber, identificationType);

        // Act
        boolean isValid = jwtTokenUtil.validateToken(token, identificationNumber);

        // Assert
        assertTrue(isValid);
    }

    @Test
    void testValidateToken_WithInvalidToken() {
        // Arrange
        String identificationNumber = "123456";
        String invalidIdentificationNumber = "654321";
        IdentificationType identificationType = IdentificationType.CC;
        String token = jwtTokenUtil.generateToken(identificationNumber, identificationType);

        // Act
        boolean isValid = jwtTokenUtil.validateToken(token, invalidIdentificationNumber);

        // Assert
        assertFalse(isValid);
    }

    @Test
    void testGetIdentificationNumberFromToken() {
        // Arrange
        String identificationNumber = "123456";
        IdentificationType identificationType = IdentificationType.CC;
        String token = jwtTokenUtil.generateToken(identificationNumber, identificationType);

        // Act
        String extractedIdentificationNumber = jwtTokenUtil.getIdentificationNumberFromToken(token);

        // Assert
        assertEquals(identificationNumber, extractedIdentificationNumber);
    }

    @Test
    void testIsTokenExpired_WithNonExpiredToken() {
        // Arrange
        String identificationNumber = "123456";
        IdentificationType identificationType = IdentificationType.CC;
        String token = jwtTokenUtil.generateToken(identificationNumber, identificationType);

        // Act
        boolean isExpired = ReflectionTestUtils.invokeMethod(jwtTokenUtil, "isTokenExpired", token);

        // Assert
        assertFalse(isExpired);
    }

    @Test
    void testIsTokenExpired_WithExpiredToken() {
        // Arrange
        ReflectionTestUtils.setField(jwtTokenUtil, "expiration", -3600L); // Set expiration time in the past
        String identificationNumber = "123456";
        IdentificationType identificationType = IdentificationType.CC;
        String token = jwtTokenUtil.generateToken(identificationNumber, identificationType);

        // Act
        boolean isExpired = ReflectionTestUtils.invokeMethod(jwtTokenUtil, "isTokenExpired", token);

        // Assert
        assertTrue(isExpired);
    }

    @Test
    void testGetIdentificationTypeFromToken() {
        // Arrange
        String identificationNumber = "123456";
        IdentificationType identificationType = IdentificationType.CC;
        String token = jwtTokenUtil.generateToken(identificationNumber, identificationType);

        // Act
        IdentificationType extractedType = jwtTokenUtil.getIdentificationTypeFromToken(token);

        // Assert
        assertEquals(identificationType, extractedType);
    }
}

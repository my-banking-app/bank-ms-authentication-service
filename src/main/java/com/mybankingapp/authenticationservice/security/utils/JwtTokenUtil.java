package com.mybankingapp.authenticationservice.security.utils;

import com.mybankingapp.authenticationservice.enums.IdentificationType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

/**
 * Utility class for handling JWT token operations such as generation, validation, and extraction of claims.
 */
@Component
public class JwtTokenUtil {

    /**
     * Secret key used for signing the JWT token.
     */
    @Value("${jwt.secret}")
    private String secretKey;

    /**
     * Expiration time for the JWT token in seconds.
     */
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * Generates a JWT token based on the provided identification number and type.
     *
     * @param identificationNumber the identification number of the user
     * @param identificationType the type of identification (e.g., passport, ID card)
     * @return the generated JWT token
     */
    public String generateToken(String identificationNumber, IdentificationType identificationType) {
        return Jwts.builder()
                .setSubject(identificationNumber)
                .claim("identificationType", identificationType.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .compact();
    }

    /**
     * Validates the provided JWT token by checking the identification number and expiration date.
     *
     * @param token the JWT token to validate
     * @param identificationNumber the identification number to compare with the token's subject
     * @return true if the token is valid, false otherwise
     */
    public boolean validateToken(String token, String identificationNumber) {
        return getIdentificationNumberFromToken(token).equals(identificationNumber) && !isTokenExpired(token);
    }

    /**
     * Extracts the identification number (subject) from the provided JWT token.
     *
     * @param token the JWT token
     * @return the identification number (subject) from the token
     */
    public String getIdentificationNumberFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    /**
     * Extracts the claims from the provided JWT token.
     *
     * @param token the JWT token
     * @return the claims contained in the token
     */
    private Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Base64.getDecoder().decode(secretKey))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Checks if the provided JWT token is expired.
     *
     * @param token the JWT token
     * @return true if the token is expired, false otherwise
     */
    private boolean isTokenExpired(String token) {
        return getClaimsFromToken(token).getExpiration().before(new Date());
    }

    /**
     * Extracts the identification type from the provided JWT token.
     *
     * @param token the JWT token
     * @return the identification type from the token
     */
    public IdentificationType getIdentificationTypeFromToken(String token) {
        String type = getClaimsFromToken(token).get("identificationType", String.class);
        return IdentificationType.valueOf(type);
    }
}
package com.mybankingapp.authenticationservice.security.utils;

import com.mybankingapp.authenticationservice.enums.IdentificationType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenUtil {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String identificationNumber, IdentificationType identificationType) {
        return Jwts.builder()
                .setSubject(identificationNumber)
                .claim("identificationType", identificationType.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .compact();
    }

    public boolean validateToken(String token, String identificationNumber) {
        return getIdentificationNumberFromToken(token).equals(identificationNumber) && !isTokenExpired(token);
    }

    public String getIdentificationNumberFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Base64.getDecoder().decode(secretKey))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return getClaimsFromToken(token).getExpiration().before(new Date());
    }

    public IdentificationType getIdentificationTypeFromToken(String token) {
        String type = getClaimsFromToken(token).get("identificationType", String.class);
        return IdentificationType.valueOf(type);
    }

}
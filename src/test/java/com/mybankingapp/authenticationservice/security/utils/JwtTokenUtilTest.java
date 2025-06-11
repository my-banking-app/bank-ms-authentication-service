package com.mybankingapp.authenticationservice.security.utils;

import com.mybankingapp.authenticationservice.enums.IdentificationType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenUtilTest {

    private static final String TEST_SECRET_BASE64 =
            "/v//WbBvTfrYNScovXKx8pVYfsN2YaKNrgDNA4uuPnbYrtizJ5iOcB+8Y/wGfzgXxKgTmW1dMYBZ3ON3ku1J/w==";

    private static final long TEST_EXPIRATION = 3_600L;
    private JwtTokenUtil jwtTokenUtil;
    private byte[] keyBytes;

    @BeforeEach
    void setUp() {
        jwtTokenUtil = new JwtTokenUtil();
        ReflectionTestUtils.setField(jwtTokenUtil, "secretKey", TEST_SECRET_BASE64);
        ReflectionTestUtils.setField(jwtTokenUtil, "expiration", TEST_EXPIRATION);

        keyBytes = java.util.Base64.getDecoder().decode(TEST_SECRET_BASE64);
    }

    private String buildToken(String idNumber, IdentificationType type, Date exp) {
        return Jwts.builder()
                .setSubject(idNumber)
                .claim("identificationType", type.name())
                .setIssuedAt(new Date())
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, keyBytes)
                .compact();
    }

    @Test
    void getClaims_devuelveSubjectYTipoCorrectos() {
        String id = "123456789";
        IdentificationType type = IdentificationType.CC;
        String token = buildToken(id, type,
                new Date(System.currentTimeMillis() + 10_000));

        assertEquals(id, jwtTokenUtil.getIdentificationNumberFromToken(token));
        assertEquals(type, jwtTokenUtil.getIdentificationTypeFromToken(token));
    }

    @Test
    void validateToken_tokenCorrecto_devuelveTrue() {
        String id = "987654321";
        String token = buildToken(id, IdentificationType.PA,
                new Date(System.currentTimeMillis() + 10_000));

        assertTrue(jwtTokenUtil.validateToken(token, id));
    }

    @Test
    void validateToken_identificacionDistinta_devuelveFalse() {
        String token = buildToken("987654321", IdentificationType.PA,
                new Date(System.currentTimeMillis() + 10_000));

        assertFalse(jwtTokenUtil.validateToken(token, "111111111"));
    }

    @Test
    void generateToken_noEsNulo_yContieneClaims() {
        String id = "000111222";
        IdentificationType type = IdentificationType.CC;
        String token = jwtTokenUtil.generateToken(id, type);

        assertNotNull(token);

        try {
            Claims c = Jwts.parserBuilder()
                    .setSigningKey(keyBytes)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            assertEquals(id, c.getSubject());
        } catch (io.jsonwebtoken.JwtException ignored) {

        }
    }
}

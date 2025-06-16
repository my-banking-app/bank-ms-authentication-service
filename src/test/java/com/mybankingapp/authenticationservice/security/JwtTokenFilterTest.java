package com.mybankingapp.authenticationservice.security;

import com.mybankingapp.authenticationservice.enums.IdentificationType;
import com.mybankingapp.authenticationservice.security.utils.JwtTokenUtil;
import com.mybankingapp.authenticationservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtTokenFilterTest {

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private UserService userService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private JwtTokenFilter jwtTokenFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoFilterInternal_WithValidToken() throws ServletException, IOException {
        // Arrange
        String token = "Bearer validToken";
        String identificationNumber = "123456";
        IdentificationType identificationType = IdentificationType.CC;
        String username = "user";

        when(request.getHeader("Authorization")).thenReturn(token);
        when(jwtTokenUtil.getIdentificationNumberFromToken("validToken")).thenReturn(identificationNumber);
        when(jwtTokenUtil.getIdentificationTypeFromToken("validToken")).thenReturn(identificationType);
        when(userService.loadUserByIdentificationTypeAndNumber(identificationType, identificationNumber)).thenReturn(userDetails);
        when(jwtTokenUtil.validateToken("validToken", username)).thenReturn(true);
        when(userDetails.getUsername()).thenReturn(username);

        // Act
        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain, times(1)).doFilter(request, response);
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertTrue(SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken);
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        assertEquals(userDetails, authentication.getPrincipal());
    }

    @Test
    void testDoFilterInternal_WithInvalidToken() throws ServletException, IOException {
        // Arrange
        String token = "Bearer invalidToken";
        String identificationNumber = "123456";
        IdentificationType identificationType = IdentificationType.CC;
        String username = "user";

        when(request.getHeader("Authorization")).thenReturn(token);
        when(jwtTokenUtil.getIdentificationNumberFromToken("invalidToken")).thenReturn(identificationNumber);
        when(jwtTokenUtil.getIdentificationTypeFromToken("invalidToken")).thenReturn(identificationType);
        when(userService.loadUserByIdentificationTypeAndNumber(identificationType, identificationNumber)).thenReturn(userDetails);
        when(jwtTokenUtil.validateToken(anyString(), anyString())).thenReturn(false);

        // Act
        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain, times(1)).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void testDoFilterInternal_WithoutToken() throws ServletException, IOException {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn(null);

        // Act
        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain, times(1)).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }
}

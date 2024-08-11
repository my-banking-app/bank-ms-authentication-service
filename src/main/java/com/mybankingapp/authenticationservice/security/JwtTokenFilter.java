package com.mybankingapp.authenticationservice.security;

import com.mybankingapp.authenticationservice.enums.IdentificationType;
import com.mybankingapp.authenticationservice.security.utils.JwtTokenUtil;
import com.mybankingapp.authenticationservice.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter that processes JWT tokens for authentication.
 */
public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenUtil jwtTokenUtil;
    private UserService userService;

    /**
     * Filters incoming requests to check for a valid JWT token.
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @param filterChain the filter chain
     * @throws ServletException if an error occurs during filtering
     * @throws IOException if an I/O error occurs during filtering
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = getJwtFromRequest(request);
        if (jwt != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            String identificationNumber = jwtTokenUtil.getIdentificationNumberFromToken(jwt);
            IdentificationType identificationType = jwtTokenUtil.getIdentificationTypeFromToken(jwt);
            UserDetails userDetails = userService.loadUserByIdentificationTypeAndNumber(identificationType, identificationNumber);

            if (jwtTokenUtil.validateToken(jwt, userDetails.getUsername())) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Extracts the JWT token from the request header.
     *
     * @param request the HTTP request
     * @return the JWT token, or null if not present
     */
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
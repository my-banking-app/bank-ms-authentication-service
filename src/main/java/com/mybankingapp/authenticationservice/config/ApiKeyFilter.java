package com.mybankingapp.authenticationservice.config;



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

public class ApiKeyFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(ApiKeyFilter.class);

    private static final String API_KEY_HEADER = "x-api-key";

    private static final String API_KEY = "KrgrqeSrMrEtXZNikyzGdCQrSA9XpcKFjnfXfK6J18uVD4VtPLyAccSHYAgCK6qZZom08CpPMQt3yPbWJ7EjuvTluMNliOwnIc0xblDWDTiMaAIiQ36ba2Qj4VkRuq2N";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String apiKey = request.getHeader(API_KEY_HEADER);
        logger.info("Request received for URI: {}", request.getRequestURI());

        if (apiKey == null) {
            logger.warn("No API key found in request headers");
        } else {
            logger.info("API key found: {}", apiKey);
        }

        if (API_KEY.equals(apiKey)) {
            logger.info("API key is valid");
            filterChain.doFilter(request, response);
        } else {
            logger.error("Invalid API key");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}

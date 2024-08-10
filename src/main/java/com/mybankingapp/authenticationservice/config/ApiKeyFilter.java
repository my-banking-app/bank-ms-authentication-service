package com.mybankingapp.authenticationservice.config;

import com.mybankingapp.authenticationservice.utils.SendUnauthorizedResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

public class ApiKeyFilter extends OncePerRequestFilter {
    //private static final Logger logger = LoggerFactory.getLogger(ApiKeyFilter.class);

    private SendUnauthorizedResponse sendUnauthorizedResponse = new SendUnauthorizedResponse();
    private static final String API_KEY_HEADER = "x-api-key";

    @Value("${api.key}")
    private String API_KEY;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String apiKeyHeader = request.getHeader(API_KEY_HEADER);
        //logger.info("Request received for URI: {}", request.getRequestURI());

        if (apiKeyHeader == null) {
            //logger.warn("No API key found in request headers");
            sendUnauthorizedResponse.sendUnauthorizedResponse(response, "Unauthorized: No API key found in request headers");
            return;
        }

        //logger.info("API key found: {}", apiKeyHeader);
        //logger.info("Expected API key: {}", API_KEY);

        if (!API_KEY.equals(apiKeyHeader)) {
            //logger.error("Invalid API key");
            sendUnauthorizedResponse.sendUnauthorizedResponse(response, "Unauthorized: Invalid API key");
            return;
        }

        //logger.info("API key is valid");
        filterChain.doFilter(request, response);
    }

}

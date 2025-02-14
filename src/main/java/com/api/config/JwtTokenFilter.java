package com.api.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Optional;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String BEARER_TOKEN_PREFIX = "Bearer ";
    private static final String ATTRIBUTE_USER_DETAIL = "DionisioUD";
    private final JwtTokenUtil jwtTokenUtil;


    public JwtTokenFilter(JwtTokenUtil jwtTokenUtil) {

        this.jwtTokenUtil = jwtTokenUtil;
    }


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest req, @NonNull HttpServletResponse resp, @NonNull FilterChain chain) {
        Optional.ofNullable(getTokenFromRequest(req))
                .filter(jwtTokenUtil::validate)
                .map(jwtTokenUtil::getUserDetails)
                .ifPresentOrElse(dionisioUD -> setAuthenticationContext(req, dionisioUD), () -> proceedFilter(chain, req, resp));
    }

    private void setAuthenticationContext(HttpServletRequest req, DionisioUD dionisioUD) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                dionisioUD.getUsername(), null, dionisioUD.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        req.setAttribute(ATTRIBUTE_USER_DETAIL, dionisioUD);
    }

    private void proceedFilter(FilterChain chain, HttpServletRequest req, HttpServletResponse resp) {
        try {
            chain.doFilter(req, resp);
        } catch (Exception e) {
            throw new RuntimeException("Error processing request without authentication", e);
        }
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .filter(header -> header.startsWith(BEARER_TOKEN_PREFIX))
                .map(header -> header.substring(BEARER_TOKEN_PREFIX.length()).trim())
                .orElse(null);
    }
}

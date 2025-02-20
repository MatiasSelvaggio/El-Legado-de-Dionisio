package com.api.service;

import com.api.config.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtService {


    private final JwtConfig jwtConfig;

    @Autowired
    public JwtService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public void printJwtConfig() {
        System.out.println("JWT Secret: " + jwtConfig.getSecret());
        System.out.println("JWT Expiration: " + jwtConfig.getExpiration());
        System.out.println("Backoffice Issuer: " + jwtConfig.getIssuer().getBackoffice());
        System.out.println("User Issuer: " + jwtConfig.getIssuer().getUser());
    }
}

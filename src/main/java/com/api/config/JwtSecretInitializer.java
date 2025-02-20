package com.api.config;

import com.api.util.Jwt;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtSecretInitializer {

    private final JwtConfig jwtConfig;

    @Autowired
    public JwtSecretInitializer(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @PostConstruct
    public void init() {
        Jwt.setJwtSecret(jwtConfig.getSecret());
    }
}

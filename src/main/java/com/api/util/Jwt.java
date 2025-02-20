package com.api.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public enum Jwt {

    INSTANCE;

    public final static String AUTHORITY_CLAIM_NAME = "xRoles";

    public String getJWT(String id, String username, String issuer, String jwtExpirationInHoursStr, Roles role){
        long jwtExpirationInHours = Long.parseLong(jwtExpirationInHoursStr);
        LocalDateTime nowPlusValidity = LocalDateTime.now().plusHours(jwtExpirationInHours);
        Date validity = Date.from(nowPlusValidity.atZone(ZoneId.systemDefault()).toInstant());
        Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .subject(username)
                .id(id)
                .issuedAt(now)
                .expiration(validity)
                .issuer(issuer)
                .claim(AUTHORITY_CLAIM_NAME, List.of(role))
                .signWith(getKey())
                .compact();
    }

    public List<String> getRolesFromToken(String token) {
        try {
            return castListRole(parseToken(token).getPayload().get(AUTHORITY_CLAIM_NAME, List.class));
        } catch (Exception e){
            return null;
        }
    }

    public String getIdFromToken(String token) {
        try {
            return parseToken(token).getPayload().getId();
        } catch (Exception e) {
            return "";
        }
    }

    private List<String> castListRole(Object rolesList) {
        return rolesList instanceof List<?> obj ? obj.stream().map(
                it -> (String) it).collect(Collectors.toList()
        ) : new ArrayList<>();
    }

    private static String jwtSecret;

    public static void setJwtSecret(String secret) {
        jwtSecret = secret;
    }

    public static SecretKey getKey() {
        if (jwtSecret == null) {
            throw new IllegalStateException("JWT secret key is not initialized.");
        }
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }


    private Jws<Claims> parseToken(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token);
    }
}

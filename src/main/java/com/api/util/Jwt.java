package com.api.util;

import io.github.cdimascio.dotenv.Dotenv;
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

    public String getJWT(String id, String username, String issuer, Roles role){
        Dotenv dotenv = Dotenv.load();

        LocalDateTime nowPlusValidity = LocalDateTime.now().plusHours(Long.getLong(dotenv.get("JWTExpirationInHours")));
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
                .toString();
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

    public static SecretKey getKey() {
        Dotenv dotenv = Dotenv.load();
        return Keys.hmacShaKeyFor(dotenv.get("JWTSecret").getBytes());
    }

    private Jws<Claims> parseToken(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token);
    }
}

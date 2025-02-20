package com.api.config;

import com.api.util.Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import static com.api.util.Jwt.getKey;

@Component
public class JwtTokenUtil implements Serializable {

    @Serial
    private static final long serialVersionUID = -23871239876592L;

    private final String userIssuer;

    public JwtTokenUtil(@Value("${jwt.issuer.user}")String userIssuer) {
        this.userIssuer = userIssuer;
    }

    public String getUserNameFromToken(String token) {
        final Claims claims = getClaimsFromToken(token);
        return claims != null ? claims.getSubject() : null;
    }

    private Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser().verifyWith(getKey())
                    .requireIssuer(userIssuer)
                    .build().parseSignedClaims(token).getPayload();

        } catch (Exception e) {
            return null;
        }
    }

    public Boolean validate(String token) {
        return !isTokenExpired(token);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationFromToken(token);
        return expiration != null && expiration.before(new Date());

    }

    private Date getExpirationFromToken(String token) {
        final Claims claims = getClaimsFromToken(token);
        return claims != null ? claims.getExpiration() : null;
    }

    public DionisioUD getUserDetails(String token) {
        return new DionisioUD(
                Jwt.INSTANCE.getIdFromToken(token),
                getUserNameFromToken(token),
                true,
                Jwt.INSTANCE.getRolesFromToken(token)
        );
    }
}

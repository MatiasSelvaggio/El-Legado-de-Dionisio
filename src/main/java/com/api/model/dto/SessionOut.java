package com.api.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionOut {

    private String message;

    private String tokenJwt;

    private Long expiration;

    public SessionOut(String message, String tokenJwt, Long expiration) {
        this.message = message;
        this.tokenJwt = tokenJwt;
        this.expiration = expiration;
    }
}

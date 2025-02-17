package com.api.controller;

import com.api.controller.swagger.AuthSwagger;
import com.api.model.dto.LoginIn;
import com.api.model.dto.RegisterUserIn;
import com.api.model.dto.SessionOut;
import com.api.service.inter.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthSwagger {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<SessionOut> registerUser(@Valid @RequestBody RegisterUserIn registerUserIn) {

        return new ResponseEntity<SessionOut>(this.authService.registerUser(registerUserIn), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<SessionOut> loginUser(LoginIn loginIn) {

        return new ResponseEntity<SessionOut>(this.authService.loginUser(loginIn), HttpStatus.OK);
    }
}

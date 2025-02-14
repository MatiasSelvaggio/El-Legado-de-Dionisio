package com.api.controller;

import com.api.controller.swagger.AuthSwagger;
import com.api.model.dto.RegisterUserIn;
import com.api.model.dto.SessionOut;
import com.api.service.AuthServiceImpl;
import com.api.service.inter.AuthService;
import com.api.service.inter.UserService;
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

    private final AuthServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<SessionOut> registerUser(@Valid @RequestBody RegisterUserIn registerUserIn) {

        return new ResponseEntity<SessionOut>(this.authService.registerUser(registerUserIn), HttpStatus.CREATED);
    }
}

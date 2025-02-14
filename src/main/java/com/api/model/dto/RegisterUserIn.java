package com.api.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserIn {

    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotBlank(message = "lastName cannot be blank")
    private String lastName;

    @NotBlank(message = "password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @Email(message = "email must be a valid email address")
    private String email;
}

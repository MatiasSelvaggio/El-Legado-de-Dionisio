package com.api.model.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserIn {

    private String name;

    private String lastName;

    private String password;

    private String email;

}

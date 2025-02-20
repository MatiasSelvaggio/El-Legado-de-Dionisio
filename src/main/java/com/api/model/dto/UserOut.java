package com.api.model.dto;


import com.api.model.entity.User;
import com.api.util.Roles;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserOut {

    private String name;

    private String lastName;

    private String email;

    private Long idUser;

    private Roles rol;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime created;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime deleted;

    public UserOut() {}

    public UserOut(User user) {
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.idUser = user.getIdUser();
        this.rol = user.getRole();
        this.created = user.getCreated();
        this.deleted = user.getDeleted();
    }
}

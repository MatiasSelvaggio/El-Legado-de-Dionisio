package com.api.model.entity;

import com.api.util.Roles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Roles role;

    @Column(name = "deleted")
    private LocalDateTime deleted;

    @OneToMany(mappedBy = "user")
    private Set<Event> events;

    @OneToMany(mappedBy = "user")
    private Set<Ticket> tickets;

    @OneToMany(mappedBy = "user")
    private Set<Attendance> attendances;

    public User(Roles role, String password, String email, String lastName, String name) {
        this.role = role;
        this.password = password;
        this.email = email;
        this.lastName = lastName;
        this.name = name;
        this.created = LocalDateTime.now();
    }

    public User() {
    }
}

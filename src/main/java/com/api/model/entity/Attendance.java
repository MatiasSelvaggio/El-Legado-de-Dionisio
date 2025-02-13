package com.api.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Attendance")
public class Attendance {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_Event", nullable = false)
    private Event event;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(name = "status", nullable = false)
    private String status;
}

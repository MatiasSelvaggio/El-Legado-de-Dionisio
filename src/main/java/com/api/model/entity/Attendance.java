package com.api.model.entity;

import com.api.model.composite.key.AttendanceId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Attendance")
@IdClass(AttendanceId.class)
@NoArgsConstructor
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


    public Attendance(Event event, User user, String status) {
        this.event = event;
        this.user = user;
        this.status = status;
    }
}

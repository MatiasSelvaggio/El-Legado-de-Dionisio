package com.api.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Event")
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event")
    private Long id_event;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date_start", nullable = false)
    private LocalDateTime date_start;

    @Column(name = "date_end", nullable = false)
    private LocalDateTime date_end;

    @Column(name = "localidad", nullable = false)
    private String localidad;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "ticket_limit")
    private Integer ticket_limit;

    @Column(name = "tickets_sold")
    private Integer tickets_sold;

    @OneToMany(mappedBy = "event")
    private Set<Ticket> tickets;

    @OneToMany(mappedBy = "event")
    private Set<Attendance> attendances;
}

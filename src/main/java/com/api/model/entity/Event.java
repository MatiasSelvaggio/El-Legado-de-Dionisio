package com.api.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Event")
@Getter
@Setter
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event")
    private Long idEvent;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date_start", nullable = false)
    private LocalDateTime dateStart;

    @Column(name = "date_end", nullable = false)
    private LocalDateTime dateEnd;

    @Column(name = "place", nullable = false)
    private String place;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "ticket_limit")
    private Integer ticketLimit;

    @Column(name = "tickets_sold")
    private Integer ticketsSold;

    @Column(name = "deleted")
    private LocalDateTime deleted;

    @OneToMany(mappedBy = "event")
    private Set<Ticket> tickets;

    @OneToMany(mappedBy = "event")
    private Set<Attendance> attendances;


    public Event(Integer ticketLimit, String status, User user, String place, LocalDateTime dateStart, LocalDateTime dateEnd, String name) {
        this.ticketsSold = 0;
        this.ticketLimit = ticketLimit;
        this.status = status;
        this.user = user;
        this.place = place;
        this.dateEnd = dateEnd;
        this.dateStart = dateStart;
        this.name = name;
    }
}

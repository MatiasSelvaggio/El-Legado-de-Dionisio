package com.api.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Ticket")
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    private Long idTicket;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_Event", nullable = false)
    private Event event;

    @Column(name = "value", nullable = false)
    private Double value;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "code", nullable = false)
    private String code;


    public Ticket(User user, Event event, Double value, Integer quantity) {
        this.user = user;
        this.event = event;
        this.value = value;
        this.quantity = quantity;
        this.code = UUID.randomUUID().toString();
    }
}

package com.api.model.dto;

import com.api.model.entity.Event;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventOut {

    private Long idEvent;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dateStart;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dateEnd;
    private String place;
    private UserOut user;
    private String status;
    private Integer ticketLimit;
    private Integer ticketsSold;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime created;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime deleted;

    public EventOut(Event event) {
        this.idEvent = event.getIdEvent();
        this.name = event.getName();
        this.dateStart = event.getDateStart();
        this.dateEnd = event.getDateEnd();
        this.place = event.getPlace();
        this.user = new UserOut(event.getUser());
        this.status = event.getStatus();
        this.ticketLimit = event.getTicketLimit();
        this.ticketsSold = event.getTicketsSold();
        this.created = event.getCreated();
        this.deleted = event.getDeleted();
    }
}

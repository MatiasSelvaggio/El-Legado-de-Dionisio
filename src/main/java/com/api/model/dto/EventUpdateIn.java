package com.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventUpdateIn {

    private Long ownerId;

    private Integer ticket_limit;

    private String status;

    private String address;

    private LocalDateTime dateEnd;

    private LocalDateTime dateStart;

    private String name;
}

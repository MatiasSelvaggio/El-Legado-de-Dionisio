package com.api.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventIn {

    private Long ownerId;

    @Min(10)
    private Integer ticketLimit;

    @NotNull(message = "you must send ticketPrice")
    private Double ticketPrice;

    @NotBlank(message = "you must send status")
    private String status;

    @NotBlank(message = "you must send address")
    private String address;

    @NotNull(message = "you must send dateEnd")
    private LocalDateTime dateEnd;

    @NotNull(message = "you must send dateStart")
    private LocalDateTime dateStart;

    @NotBlank(message = "you must send name")
    private String name;

}

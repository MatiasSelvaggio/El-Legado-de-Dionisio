package com.api.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketIn {

    @NotNull(message = "You must send field idEvent")
    private Long idEvent;
    @NotNull(message = "You must send field quantity")
    private Integer quantity;
}

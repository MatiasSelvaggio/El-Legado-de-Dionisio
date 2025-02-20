package com.api.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceIn {

    @NotNull(message = "must send idTicket")
    private Long idTicket;

    @NotBlank(message = "must send code")
    private String code;
}

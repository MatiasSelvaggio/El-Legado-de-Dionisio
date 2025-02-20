package com.api.model.dto;

import com.api.model.entity.Attendance;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateOut {

    private String message;

    public ValidateOut(Attendance attendance) {
        this.message = String.format("Bienvenido a %s, preparate para disfrustar %s", attendance.getEvent().getName(), attendance.getUser().getName());
    }
}

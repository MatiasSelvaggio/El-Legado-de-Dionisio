package com.api.model.dto;

import com.api.model.entity.Attendance;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceOut {

    private EventOut event;
    private UserOut user;
    private String status;

    public AttendanceOut(Attendance attendance) {
        this.event = new EventOut(attendance.getEvent());
        this.user = new UserOut(attendance.getUser());
        this.status = attendance.getStatus();
    }
}

package com.api.service.inter;

import com.api.model.dto.AttendanceIn;
import com.api.model.dto.AttendanceOut;
import com.api.model.dto.ValidateOut;

import java.util.List;

public interface AttendanceService {
    ValidateOut validateAttendance(AttendanceIn attendanceIn, Long idUser);

    List<AttendanceOut> getAttendanceFromIdEvent(Long idEvent, Long idUser);
}

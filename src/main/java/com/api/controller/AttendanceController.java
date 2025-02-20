package com.api.controller;

import com.api.config.DionisioUD;
import com.api.controller.swagger.AttendanceSwagger;
import com.api.model.dto.AttendanceIn;
import com.api.model.dto.AttendanceOut;
import com.api.model.dto.ValidateOut;
import com.api.service.inter.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController implements AttendanceSwagger {

    private final AttendanceService attendanceService;


    @Secured({"ROLE_ADMIN","ROLE_ORGANIZER"})
    @PostMapping()
    public ResponseEntity<ValidateOut> validateAttendance(@RequestBody AttendanceIn attendanceIn, @RequestAttribute("DionisioUD") DionisioUD dionisioUD) {
        return new ResponseEntity<>(this.attendanceService.validateAttendance(attendanceIn, Long.parseLong(dionisioUD.getId())), HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN","ROLE_ORGANIZER"})
    @GetMapping("/{idEvent}")
    public ResponseEntity<List<AttendanceOut>> getAttendanceFromIdEvent(@PathVariable("idEvent") Long idEvent,  @RequestAttribute("DionisioUD") DionisioUD dionisioUD) {
        return new ResponseEntity<>(this.attendanceService.getAttendanceFromIdEvent(idEvent, Long.parseLong(dionisioUD.getId())), HttpStatus.OK);
    }
}

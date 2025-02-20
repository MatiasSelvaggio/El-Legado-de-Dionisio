package com.api.service;

import com.api.exception.ApiException;
import com.api.model.dto.AttendanceIn;
import com.api.model.dto.AttendanceOut;
import com.api.model.dto.ValidateOut;
import com.api.model.entity.Attendance;
import com.api.model.entity.Event;
import com.api.model.entity.Ticket;
import com.api.model.entity.User;
import com.api.repository.AttendanceRepository;
import com.api.repository.EventRepository;
import com.api.repository.TicketRepository;
import com.api.repository.UserRepository;
import com.api.service.inter.AttendanceService;
import com.api.util.Roles;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    public ValidateOut validateAttendance(AttendanceIn attendanceIn, Long idUser) {
        User user = this.getUser(idUser);
        Ticket ticket = this.getTicket(attendanceIn.getIdTicket());
        if (!ticket.getCode().equals(attendanceIn.getCode()) ) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid ticket");
        }
        if (user.getRole().equals(Roles.ROLE_ORGANIZER)) {
            if (!ticket.getEvent().getUser().getIdUser().equals(idUser)) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "this event its not yours to validate");
            }
        }

        Optional<Attendance> optionalAttendance = this.attendanceRepository.findByEventIdEventAndUserIdUser(ticket.getEvent().getIdEvent(), ticket.getUser().getIdUser());
        if (optionalAttendance.isPresent()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "This ticket is already use");
        }

        Attendance attendance = new Attendance(ticket.getEvent(), ticket.getUser(), "COMPLETE");

        return new ValidateOut(this.attendanceRepository.save(attendance));
    }

    public List<AttendanceOut> getAttendanceFromIdEvent(Long idEvent, Long idUser) {
        User user = this.getUser(idUser);
        Event event = this.getEvent(idEvent);
        if (user.getRole().equals(Roles.ROLE_ORGANIZER)) {
            if (!event.getUser().getIdUser().equals(idUser)) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "this event is not yours");
            }
        }
        return this.attendanceRepository.findByEventIdEvent(idEvent).stream().map(AttendanceOut::new).toList();
    }

    private User getUser(Long idUser) {
        Optional<User> user = this.userRepository.findById(idUser);
        if (user.isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "User not found");
        }
        return user.get();
    }

    private Event getEvent(Long idEvent) {
        Optional<Event> event = this.eventRepository.findById(idEvent);
        if (event.isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND, " Event not found");
        }
        return event.get();
    }

    private Ticket getTicket(Long idTicket) {
        Optional<Ticket> ticket = this.ticketRepository.findById(idTicket);
        if (ticket.isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND, " Event not found");
        }
        return ticket.get();
    }
}

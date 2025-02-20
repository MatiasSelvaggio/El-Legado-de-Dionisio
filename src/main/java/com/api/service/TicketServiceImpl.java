package com.api.service;

import com.api.exception.ApiException;
import com.api.model.dto.TicketIn;
import com.api.model.dto.TicketOut;
import com.api.model.entity.Event;
import com.api.model.entity.Ticket;
import com.api.model.entity.User;
import com.api.repository.EventRepository;
import com.api.repository.TicketRepository;
import com.api.repository.UserRepository;
import com.api.service.inter.TicketService;
import com.api.util.JSON;
import com.api.util.QrCodeGenerator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public BufferedImage buyTicket(TicketIn ticketIn, Long idUser) {
        User user = this.getUser(idUser);
        Event event = this.getEvent(ticketIn.getIdEvent());
        if (event.getTicketLimit().equals(event.getTicketsSold())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Sold Out tickets for this event");
        }
        if (event.getTicketLimit() < event.getTicketsSold() + ticketIn.getQuantity()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "You cant buy this cant of tickets");
        }
        event.setTicketsSold(event.getTicketsSold() + ticketIn.getQuantity());
        this.eventRepository.save(event);
        Ticket ticket = new Ticket(user,event,event.getTicketPrice(), ticketIn.getQuantity());
        try {
            this.ticketRepository.save(ticket);
            String textOut = JSON.stringify(new TicketOut.FormatOut(ticket));
            return QrCodeGenerator.createQRwithText(textOut, ticket.getEvent().getName(), ticket.getEvent().getDateStart().toString());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.FAILED_DEPENDENCY, "Fail creation of Qr");
        }

    }

    public List<TicketOut.FormatOut> getMyTickets(Long idUser) {
        this.getUser(idUser);
        return this.ticketRepository.findByUserIdUser(idUser).stream().map(TicketOut.FormatOut::new).toList();
    }

    public List<TicketOut.FormatOut> getAllTickets() {
        return this.ticketRepository.findAll(Sort.by(Sort.Direction.ASC, "event.dateStart")).stream().map(TicketOut.FormatOut::new).toList();
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
}

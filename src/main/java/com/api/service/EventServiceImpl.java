package com.api.service;

import com.api.exception.ApiException;
import com.api.model.dto.EventIn;
import com.api.model.dto.EventOut;
import com.api.model.dto.EventUpdateIn;
import com.api.model.dto.PageResponseDto;
import com.api.model.entity.Event;
import com.api.model.entity.User;
import com.api.repository.EventRepository;
import com.api.repository.UserRepository;
import com.api.repository.specification.QuerySpecification;
import com.api.service.inter.EventService;
import com.api.util.Roles;
import com.api.util.Validator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventOut createEvent(EventIn eventIn, Long idUser) {
        return this.userRepository.findById(idUser).map(user -> {
            switch (user.getRole()){
                case ROLE_ADMIN -> {
                    Event event = createEventWithOwner(eventIn);
                    return new EventOut(this.eventRepository.save(event));
                }
                case ROLE_ORGANIZER -> {
                    Event event = new Event(eventIn.getTicketLimit(), eventIn.getTicketPrice(),eventIn.getStatus(), user, eventIn.getAddress(), eventIn.getDateStart(), eventIn.getDateEnd(), eventIn.getName());
                    return new EventOut(this.eventRepository.save(event));
                }
                case null, default -> throw new ApiException(HttpStatus.BAD_REQUEST, "user must be organizer");
            }
        }).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "user not found"));

    }

    private Event createEventWithOwner(EventIn eventIn) {
        if (eventIn.getOwnerId() == null)
            throw new ApiException(HttpStatus.BAD_REQUEST, "You must send a owner id from a organizer to create a event as a Admin");
        Optional<User> optionalOwnerUser = this.userRepository.findById(eventIn.getOwnerId());
        if (optionalOwnerUser.isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND, "owner user not found");
        User ownerUser = optionalOwnerUser.get();
        if (ownerUser.getRole().equals(Roles.ROLE_ORGANIZER))
            throw new ApiException(HttpStatus.BAD_REQUEST, "owner user must be organizer");

        return new Event(eventIn.getTicketLimit(), eventIn.getTicketPrice(), eventIn.getStatus(), ownerUser, eventIn.getAddress(), eventIn.getDateStart(), eventIn.getDateEnd(), eventIn.getName());
    }

    public PageResponseDto<EventOut> getAvailableEvent(Pageable pageable, String search) {
        Specification<Event> spec = QuerySpecification.searchEventByIdEventOrNameOrPlaceOrDateStartOrDateEndOrStatusOrCreatedOrTicketPriceOrTicketLimitOrTicketSold(search);
        Page<Event> page = this.eventRepository.findAll(spec, pageable);
        List<EventOut> eventOutList = page.getContent().stream().map(EventOut::new).toList();
        return new PageResponseDto<EventOut>(page.getTotalElements(), page.getTotalPages(), eventOutList);
    }

    public EventOut getEventById(Long idEvent) {
        return this.eventRepository.findById(idEvent)
                .map(EventOut::new)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Event with that id don't exist") );
    }


    public EventOut updateEventById(Long idEvent, EventUpdateIn input, Long idUser) {
        return this.userRepository.findById(idUser).map(user -> {
            if (user.getRole().equals(Roles.ROLE_USER)) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "owner user must be organizer");
            }
            Event event = returnEventById(idEvent);
            if (!Validator.isNullOrBlank(input.getAddress())){
                event.setPlace(input.getAddress());
            }
            if (Validator.isNullOrBlank(input.getStatus())){
                event.setStatus(input.getStatus());
            }
            if (Validator.isNullOrBlank(input.getName())) {
                event.setName(input.getName());
            }
            if (input.getDateStart() != null){
                event.setDateStart(input.getDateStart());
            }
            if (input.getDateEnd() != null){
                event.setDateEnd(input.getDateEnd());
            }

            if (user.getRole().equals(Roles.ROLE_ADMIN)) {
                if (input.getOwnerId() != null){
                    Optional<User> optionalOwnerUser = this.userRepository.findById(input.getOwnerId());
                    if (optionalOwnerUser.isEmpty())
                        throw new ApiException(HttpStatus.NOT_FOUND, "owner user not found");
                    User ownerUser = optionalOwnerUser.get();
                    if (!ownerUser.getRole().equals(Roles.ROLE_ORGANIZER))
                        throw new ApiException(HttpStatus.BAD_REQUEST, "owner user must be organizer");
                    event.setUser(ownerUser);
                }
            }
            return new EventOut(this.eventRepository.save(event));
        }).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "user not found"));
    }

    private Event returnEventById(Long idEvent) {
        Optional<Event> event = this.eventRepository.findByIdEventAndDeletedIsNull(idEvent);
        if (event.isEmpty()){
            throw new ApiException(HttpStatus.NOT_FOUND, String.format("Event not found with id: %d", idEvent));
        }
        return event.get();
    }

    private User returnUserById(Long idUser) {
        Optional<User> user = this.userRepository.findById(idUser);
        if (user.isEmpty()){
            throw new ApiException(HttpStatus.NOT_FOUND, String.format("Event not found with id: %d", idUser));
        }
        return user.get();
    }

    public void deleteEventById(Long idEvent, Long idUser) {
        User user = returnUserById(idUser);
        Event event = returnEventById(idEvent);
        if (user.getRole().equals(Roles.ROLE_ADMIN)){
            event.setDeleted(LocalDateTime.now());
        } else if (event.getUser().getIdUser().equals(idUser)) {
            event.setDeleted(LocalDateTime.now());
        }else {
            throw new ApiException(HttpStatus.BAD_REQUEST, "this user can deleted this event because he is not the organizer");
        }
        this.eventRepository.save(event);
    }
}

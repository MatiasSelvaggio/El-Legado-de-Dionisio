package com.api.controller;

import com.api.config.DionisioUD;
import com.api.controller.swagger.EventSwagger;
import com.api.model.dto.EventIn;
import com.api.model.dto.EventOut;
import com.api.model.dto.EventUpdateIn;
import com.api.service.inter.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController implements EventSwagger {

    private final EventService eventService;


    @PostMapping()
    @Secured({"ROLE_ADMIN", "ROLE_ORGANIZER"})
    public ResponseEntity<EventOut> createEvent(@RequestBody @Valid EventIn eventIn,@RequestAttribute("DionisioUD") DionisioUD dionisioUD) {
        return new ResponseEntity<>(this.eventService.createEvent(eventIn, Long.valueOf(dionisioUD.getId())), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<EventOut>> getAvailableEvent() {
        return new ResponseEntity<>(this.eventService.getAvailableEvent(), HttpStatus.OK);
    }

    @GetMapping("{idEvent}")
    public ResponseEntity<EventOut> getEventById(@PathVariable("idEvent") Long idEvent) {
        return new ResponseEntity<>(this.eventService.getEventById(idEvent), HttpStatus.OK);
    }

    @PutMapping("{idEvent}")
    @Secured({"ROLE_ADMIN", "ROLE_ORGANIZER"})
    public ResponseEntity<EventOut> updateEventById(@PathVariable Long idEvent, @RequestBody @Valid EventUpdateIn input, @RequestAttribute("DionisioUD") DionisioUD dionisioUD) {
        return new ResponseEntity<>(this.eventService.updateEventById(idEvent, input, Long.valueOf(dionisioUD.getId())), HttpStatus.OK);
    }

    @DeleteMapping("{idEvent}")
    @Secured({"ROLE_ADMIN", "ROLE_ORGANIZER"})
    public ResponseEntity<Void> deleteEventById(@PathVariable Long idEvent,  @RequestAttribute("DionisioUD")DionisioUD dionisioUD) {
        this.eventService.deleteEventById(idEvent, Long.valueOf(dionisioUD.getId()));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

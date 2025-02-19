package com.api.service.inter;


import com.api.model.dto.EventIn;
import com.api.model.dto.EventOut;
import com.api.model.dto.EventUpdateIn;

import java.util.List;

public interface EventService {
    EventOut createEvent(EventIn eventIn, Long idUser);
    List<EventOut> getAvailableEvent();
    EventOut getEventById(Long idEvent);
    EventOut updateEventById(Long idEvent, EventUpdateIn input, Long idUser);
    void deleteEventById(Long idEvent, Long idUser);
}

package com.api.service.inter;


import com.api.model.dto.EventIn;
import com.api.model.dto.EventOut;
import com.api.model.dto.EventUpdateIn;
import com.api.model.dto.PageResponseDto;
import org.springframework.data.domain.Pageable;


public interface EventService {
    EventOut createEvent(EventIn eventIn, Long idUser);
    PageResponseDto<EventOut> getAvailableEvent(Pageable pageable, String search);
    EventOut getEventById(Long idEvent);
    EventOut updateEventById(Long idEvent, EventUpdateIn input, Long idUser);
    void deleteEventById(Long idEvent, Long idUser);
}

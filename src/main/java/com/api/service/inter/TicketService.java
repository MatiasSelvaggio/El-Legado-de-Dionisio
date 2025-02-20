package com.api.service.inter;

import com.api.model.dto.TicketIn;
import com.api.model.dto.TicketOut;

import java.awt.image.BufferedImage;
import java.util.List;

public interface TicketService {

    BufferedImage buyTicket(TicketIn ticketIn, Long idUser);
    List<TicketOut.FormatOut> getMyTickets(Long idUser);
    List<TicketOut.FormatOut> getAllTickets();
}

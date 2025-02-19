package com.api.model.dto;

import com.api.model.entity.Ticket;
import com.api.util.JSON;
import com.api.util.QrCodeGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

@Getter
@Setter
public class TicketOut {

    private BufferedImage output;

    public TicketOut(Ticket ticket) throws Exception {
        String textOut = JSON.stringify(new FormatOut(ticket));
        this.output = QrCodeGenerator.createQRwithText(textOut, ticket.getEvent().getName(), ticket.getEvent().getDateStart().toString());
    }

    @Getter
    @Setter
    public static class FormatOut {
        private Long idTicket;
        private Integer quantity;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDateTime startEvent;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDateTime endEvent;
        private String eventName;
        private String place;
        private UserOut owner;

        public FormatOut(Ticket ticket) {
            this.idTicket = ticket.getIdTicket();
            this.quantity = ticket.getQuantity();
            this.endEvent = ticket.getEvent().getDateEnd();
            this.startEvent = ticket.getEvent().getDateStart();
            this.eventName = ticket.getEvent().getName();
            this.owner = new UserOut(ticket.getUser());
            this.place = ticket.getEvent().getPlace();
        }
    }
}

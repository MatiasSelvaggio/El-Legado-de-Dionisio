package com.api.controller;

import com.api.config.DionisioUD;
import com.api.controller.swagger.TicketSwagger;
import com.api.model.dto.TicketIn;
import com.api.model.dto.TicketOut;
import com.api.service.inter.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController implements TicketSwagger {

    private final TicketService ticketService;

    @Secured("ROLE_USER")
    @PostMapping(value = "/buy")
    public ResponseEntity<byte[]> buyTicket(@Valid @RequestBody TicketIn ticketIn, @RequestAttribute("DionisioUD") DionisioUD dionisioUD) {
        BufferedImage image = this.ticketService.buyTicket(ticketIn, Long.parseLong(dionisioUD.getId()));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(imageInByte, headers, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Secured("ROLE_USER")
    @GetMapping("/me")
    public ResponseEntity<List<TicketOut.FormatOut>> getMyTickets(@RequestAttribute("DionisioUD") DionisioUD dionisioUD) {
        return new ResponseEntity<>(this.ticketService.getMyTickets(Long.parseLong(dionisioUD.getId())), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/all")
    public ResponseEntity<List<TicketOut.FormatOut>> getAllTickets() {
        return new ResponseEntity<>(this.ticketService.getAllTickets(), HttpStatus.OK);
    }
}

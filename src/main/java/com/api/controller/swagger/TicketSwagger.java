package com.api.controller.swagger;

import com.api.config.DionisioUD;
import com.api.model.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Ticket", description = "TicketController")
public interface TicketSwagger {

    @Operation(summary = "Buy ticket",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TicketOut.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Input validation error",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseDto.class)
                            )
                    ),
            }
    )
    ResponseEntity<byte[]> buyTicket(TicketIn ticketIn, DionisioUD dionisioUD);

    @Operation(summary = "Buy ticket",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TicketOut.FormatOut.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Input validation error",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseDto.class)
                            )
                    ),
            }
    )
    ResponseEntity<List<TicketOut.FormatOut>> getMyTickets(DionisioUD dionisioUD);

    @Operation(summary = "Buy ticket",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TicketOut.FormatOut.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Input validation error",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseDto.class)
                            )
                    ),
            }
    )
    ResponseEntity<List<TicketOut.FormatOut>> getAllTickets();


}

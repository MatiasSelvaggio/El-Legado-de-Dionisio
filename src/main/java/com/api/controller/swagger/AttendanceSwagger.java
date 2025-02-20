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

@Tag(name = "Attendance", description = "AttendanceController")
public interface AttendanceSwagger {

    @Operation(summary = "validate Attendance",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ValidateOut.class)
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
    ResponseEntity<ValidateOut> validateAttendance(AttendanceIn attendanceIn, DionisioUD dionisioUD);

    @Operation(summary = "Get all Attendance from a event ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AttendanceOut.class))
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
    ResponseEntity<List<AttendanceOut>> getAttendanceFromIdEvent(Long idEvent, DionisioUD dionisioUD);
}

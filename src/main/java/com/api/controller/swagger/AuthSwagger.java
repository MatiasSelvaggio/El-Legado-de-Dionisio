package com.api.controller.swagger;

import com.api.model.dto.RegisterUserIn;
import com.api.model.dto.SessionOut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Auth", description = "AuthController")
public interface AuthSwagger {

    @Operation(summary = "Register User",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ResponseEntity.class)
                        )
                )
            }
    )
    ResponseEntity<SessionOut> registerUser(RegisterUserIn registerUserIn);
}

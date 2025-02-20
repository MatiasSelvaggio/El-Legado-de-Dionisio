package com.api.controller.swagger;

import com.api.model.dto.ResponseDto;
import com.api.model.dto.UpdateUserIn;
import com.api.model.dto.UserOut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "User", description = "UserController")
public interface UserSwagger {

    @Operation(summary = "Get User By Id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserOut.class)
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
    ResponseEntity<UserOut> getUserById(Long userId);

    @Operation(summary = "Get all user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserOut.class))
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
    ResponseEntity<List<UserOut>> getAllUsers();

    @Operation(summary = "Update user by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserOut.class)
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
    ResponseEntity<UserOut> updateUser(Long userId, UpdateUserIn user);

    @Operation(summary = "Delete user by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseEntity.class)
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
    ResponseEntity<Void> deleteUser(Long userId);

    @Operation(summary = "Get roles",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = String.class))
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
    ResponseEntity<List<String>> getRoles();

    @Operation(summary = "Update user rol",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserOut.class)
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
    ResponseEntity<UserOut> updateRolUser(@PathVariable("userId") Long userId, @Param("rol") String rol);
}

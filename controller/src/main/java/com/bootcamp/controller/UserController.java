package com.bootcamp.controller;

import com.bootcamp.controller.request.RequestForCreateUser;
import com.bootcamp.controller.responce.UserResponse;
import com.bootcamp.converter.UserMapper;
import com.bootcamp.domain.User;
import com.bootcamp.domain.UserRoles;
import com.bootcamp.exceptionhandle.ErrorContainer;
import com.bootcamp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import static com.bootcamp.controller.DefaultResponseTag.USER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Add new user.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User added successful.",
                            content = {@Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserResponse.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = {@Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ErrorContainer.class)))}),
                    @ApiResponse(responseCode = "500", description = "Server error",
                            content = {@Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ErrorContainer.class)))})}
    )
    @PostMapping
    public ResponseEntity<Map<String, UserResponse>> createUser(
            @RequestBody @Valid RequestForCreateUser request) {

        User user = userMapper.mapToCreate(request);

        UserRoles roleName = UserRoles.valueOf("ROLE_" + request.getRole().toUpperCase());

        user = userService.create(user, roleName);

        return new ResponseEntity<>(
                Collections.singletonMap(USER, userMapper.mapToResponse(user)),
                HttpStatus.CREATED
        );
    }
}

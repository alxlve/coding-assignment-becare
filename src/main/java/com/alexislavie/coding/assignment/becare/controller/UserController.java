package com.alexislavie.coding.assignment.becare.controller;

import com.alexislavie.coding.assignment.becare.dto.UserCreationDto;
import com.alexislavie.coding.assignment.becare.dto.UserDto;
import com.alexislavie.coding.assignment.becare.exception.EmptyRequestBodyException;
import com.alexislavie.coding.assignment.becare.exception.UnknownException;
import com.alexislavie.coding.assignment.becare.mapper.UserMapper;
import com.alexislavie.coding.assignment.becare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("user")
public class UserController {

    private final UserMapper userMapper;

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(
            @Valid @RequestBody UserCreationDto userCreationDto
    ) {
        return Optional
                .of(Optional.ofNullable(userCreationDto)
                        .orElseThrow(() -> new EmptyRequestBodyException("No user was found in the request body")))
                .map(userMapper::toUser)
                .map(userService::create)
                .map(userMapper::toUserDto)
                .map(ResponseEntity.status(HttpStatus.CREATED)::body)
                .orElseThrow(() -> new UnknownException("An unknown error has occurred while creating the user"));
    }
}

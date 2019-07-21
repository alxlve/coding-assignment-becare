package com.alexislavie.coding.assignment.becare.controller;

import com.alexislavie.coding.assignment.becare.dto.SessionCreationDto;
import com.alexislavie.coding.assignment.becare.dto.SessionDto;
import com.alexislavie.coding.assignment.becare.entity.User;
import com.alexislavie.coding.assignment.becare.exception.EmptyRequestBodyException;
import com.alexislavie.coding.assignment.becare.exception.UnknownException;
import com.alexislavie.coding.assignment.becare.mapper.SessionMapper;
import com.alexislavie.coding.assignment.becare.service.SessionService;
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
@RequestMapping
public class SessionController {

    private final SessionMapper sessionMapper;

    private final SessionService sessionService;

    private final UserService userService;

    @PostMapping(value = "/receive")
    public ResponseEntity<SessionDto> receive(
            @Valid @RequestBody SessionCreationDto sessionCreationDto
    ) {
        sessionCreationDto = Optional.ofNullable(sessionCreationDto)
                .orElseThrow(() -> new EmptyRequestBodyException("No session was found in the request body"));

        User user = userService.findByEmail(sessionCreationDto.getEmail());

        return Optional.of(sessionMapper.toSession(sessionCreationDto, user))
                .map(sessionService::receive)
                .map(sessionMapper::toSessionDto)
                .map(ResponseEntity.status(HttpStatus.ACCEPTED)::body)
                .orElseThrow(() -> new UnknownException("An unknown error has occurred while receiving the session"));
    }
}

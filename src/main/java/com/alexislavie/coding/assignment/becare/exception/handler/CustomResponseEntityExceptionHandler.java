package com.alexislavie.coding.assignment.becare.exception.handler;

import com.alexislavie.coding.assignment.becare.exception.EmptyRequestBodyException;
import com.alexislavie.coding.assignment.becare.exception.UnknownException;
import com.alexislavie.coding.assignment.becare.exception.UserEmailAlreadyExistsException;
import com.alexislavie.coding.assignment.becare.exception.UserNotFoundException;
import com.alexislavie.coding.assignment.becare.exception.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyRequestBodyException.class)
    public ResponseEntity<ExceptionDto> handleException(
            EmptyRequestBodyException ex, WebRequest request
    ) {
        ExceptionDto restApiError = ExceptionDto.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(restApiError.getStatus())
                .body(restApiError);
    }

    @ExceptionHandler(UnknownException.class)
    public ResponseEntity<ExceptionDto> handleException(
            UnknownException ex, WebRequest request
    ) {
        ExceptionDto restApiError = ExceptionDto.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(restApiError.getStatus())
                .body(restApiError);
    }

    @ExceptionHandler(UserEmailAlreadyExistsException.class)
    public ResponseEntity<ExceptionDto> handleException(
            UserEmailAlreadyExistsException ex, WebRequest request
    ) {
        ExceptionDto restApiError = ExceptionDto.builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(restApiError.getStatus())
                .body(restApiError);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleException(
            UserNotFoundException ex, WebRequest request
    ) {
        ExceptionDto restApiError = ExceptionDto.builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(restApiError.getStatus())
                .body(restApiError);
    }
}

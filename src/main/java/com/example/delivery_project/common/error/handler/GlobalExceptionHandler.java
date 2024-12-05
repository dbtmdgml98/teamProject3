package com.example.delivery_project.common.error.handler;

import com.example.delivery_project.common.error.dto.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponseDto> handleResponseStatusException(
        ResponseStatusException ex
    ) {
        return buildErrorResponseDto(
            ex.getStatusCode().value(),
            ex.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErrorResponseDto> buildErrorResponseDto(
        int errorCode, String errorMessage
    ) {

        ErrorResponseDto responseDto = new ErrorResponseDto(errorCode, errorMessage);

        return ResponseEntity.status(errorCode).body(responseDto);
    }
}

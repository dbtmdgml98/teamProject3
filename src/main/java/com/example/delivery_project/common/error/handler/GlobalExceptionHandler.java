package com.example.delivery_project.common.error.handler;

import com.example.delivery_project.common.error.dto.ErrorResponseDto;
import com.example.delivery_project.order.exception.OrderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
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
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException ex
    ) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = fieldError != null ? fieldError.getDefaultMessage() : "유효성 검사 실패";
        return buildErrorResponseDto(
            HttpStatus.BAD_REQUEST.value(),
            errorMessage
        );
    }

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ErrorResponseDto> handleOrderException(
        OrderException ex
    ) {
        return buildErrorResponseDto(
            ex.getErrorCode().getHttpStatus().value(),
            ex.getMessage()
        );
    }

    private ResponseEntity<ErrorResponseDto> buildErrorResponseDto(
        int errorCode, String errorMessage
    ) {

        ErrorResponseDto responseDto = new ErrorResponseDto(errorCode, errorMessage);

        return ResponseEntity.status(errorCode).body(responseDto);
    }

}

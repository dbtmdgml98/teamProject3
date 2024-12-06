package com.example.delivery_project.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<String> handleOrderException(OrderException orderException) {
        return new ResponseEntity<>(orderException.toString(), HttpStatus.OK);
    }
}

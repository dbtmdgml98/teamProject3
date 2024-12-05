package com.example.delivery_project.common.error.dto;

import lombok.Getter;

@Getter
public class ErrorResponseDto {

    private final int errorCode;
    private final String errorMessage;

    public ErrorResponseDto(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}

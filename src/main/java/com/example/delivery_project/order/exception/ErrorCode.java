package com.example.delivery_project.order.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum ErrorCode {
    MINIMUM_PRICE_NOT_ENOUGH("최소 주문 금액이 충족되지 않았습니다."),
    NOT_OPEN_OR_CLOSE_STORE("영업 시간이 아닙니다.");

    ErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    private final String errorMessage;
}

package com.example.delivery_project.order.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum OrderErrorCode {
    MINIMUM_PRICE_NOT_ENOUGH(HttpStatus.BAD_REQUEST, "최소 주문 금액이 충족되지 않았습니다."),
    NOT_OPEN_OR_CLOSE_STORE(HttpStatus.BAD_REQUEST, "영업 시간이 아닙니다."),
    NOT_FOUND_MENU(HttpStatus.NOT_FOUND, "존재하지 않는 메뉴입니다."),
    ORDER_ONLY_USER(HttpStatus.NOT_FOUND, "주문은 고객만 할 수 있습니다.");

    OrderErrorCode(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    private final HttpStatus httpStatus;
    private final String errorMessage;
}

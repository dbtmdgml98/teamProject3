package com.example.delivery_project.order.entity;

import lombok.Getter;

@Getter
public enum OrderStatus {
    ORDER_FINISHED("주문 완료"),
    ACCEPT_ORDER("주문 수락"),
    NOW_COOKING("조리 중"),
    COOKING_FINISHED("조리 완료"),
    NOW_DELIVERY("배달 중"),
    DELIVERY_FINISHED("배달 완료");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

}

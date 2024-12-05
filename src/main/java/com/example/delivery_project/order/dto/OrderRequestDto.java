package com.example.delivery_project.order.dto;

import lombok.Getter;

@Getter
public class OrderRequestDto {

    private final Long orderId;

    private final Long userId;

    private final Long menuId;

    private final String orderStatus;

    public OrderRequestDto(Long orderId, Long userId, Long menuId, String orderStatus) {

        this.orderId = orderId;
        this.userId = userId;
        this.menuId = menuId;
        this.orderStatus = orderStatus;

    }
}

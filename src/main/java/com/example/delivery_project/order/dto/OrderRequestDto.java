package com.example.delivery_project.order.dto;

import com.example.delivery_project.order.entity.OrderStatus;
import lombok.Getter;

@Getter
public class OrderRequestDto {

    private final Long orderId;

    private final Long userId;

    private final Long menuId;

    private final OrderStatus orderStatus;

    public OrderRequestDto(Long orderId, Long userId, Long menuId, OrderStatus orderStatus) {

        this.orderId = orderId;
        this.userId = userId;
        this.menuId = menuId;
        this.orderStatus = orderStatus;

    }
}

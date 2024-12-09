package com.example.delivery_project.order.dto;

import com.example.delivery_project.order.entity.OrderStatus;
import lombok.Getter;

@Getter
public class UpdateOrderRequestDto {
    private final Long orderId;
    private final OrderStatus orderStatus;

    public UpdateOrderRequestDto(Long orderId, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }
}

package com.example.delivery_project.order.dto;

import com.example.delivery_project.order.entity.OrderStatus;
import lombok.Getter;

@Getter
public class UpdateOrderRequestDto {
    private Long orderId;
    private OrderStatus orderStatus;
}

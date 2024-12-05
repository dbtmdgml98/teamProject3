package com.example.delivery_project.order.dto;

import com.example.delivery_project.order.entity.Order;
import lombok.Getter;

@Getter
public class OrderResponseDto {

    private Long orderId;

    private Long userId;

    private Long menuId;

    private String orderStatus;

    public OrderResponseDto (Order order) {
        this.orderId = order.getId();
        this.userId = order.getUser().getId();
        this.menuId = order.getMenu().getMenuId();
        this.orderStatus = order.getOrderStatus().toString();
    }



}

package com.example.delivery_project.order.dto;

import lombok.Getter;

@Getter
public class OrderRequestDto {

    private final Long menuId;

    public OrderRequestDto(Long menuId) {
        this.menuId = menuId;
    }
}

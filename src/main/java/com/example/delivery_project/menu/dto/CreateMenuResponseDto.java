package com.example.delivery_project.menu.dto;

import lombok.Getter;

@Getter
public class CreateMenuResponseDto {
    private Long storeId;
    private Long menuId;
    private int price;
    private String name;
    private int authority;


    public CreateMenuResponseDto(Long storeId, Long menuId, int price, String name) {
        this.storeId = storeId;
        this.menuId = menuId;
        this.price = price;
        this.name = name;
    }

}

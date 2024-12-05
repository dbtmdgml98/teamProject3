package com.example.delivery_project.menu.dto;

import com.example.delivery_project.menu.entity.MenuDelete;
import lombok.Getter;

@Getter
public class DeleteMenuRequestDto {
    private Long storeId;
    private Long menuId;
    private String name;
    private int price;
    private MenuDelete menuDelete;

    public DeleteMenuRequestDto(Long storeId, Long menuId, String name, int price, MenuDelete menuDelete) {
        this.storeId = storeId;
        this.menuId = menuId;
        this.name = name;
        this.price = price;
        this.menuDelete = menuDelete;
    }
}

package com.example.delivery_project.menu.dto;

import com.example.delivery_project.menu.entity.Menu;
import com.example.delivery_project.menu.entity.MenuDelete;
import lombok.Getter;

@Getter
public class CreateMenuResponseDto {

    private Long storeId;
    private Long menuId;
    private String name;
    private int price;
    private MenuDelete menuDelete;


    public CreateMenuResponseDto(Long storeId, Long menuId, String name, int price,
        MenuDelete menuDelete) {
        this.storeId = storeId;
        this.menuId = menuId;
        this.name = name;
        this.price = price;
        this.menuDelete = menuDelete;
    }

    public static CreateMenuResponseDto toDto(Menu menu) {
        return new CreateMenuResponseDto(
            menu.getStore().getId(),
            menu.getMenuId(),
            menu.getName(),
            menu.getPrice(),
            menu.getMenuDelete()
        );
    }

}

package com.example.delivery_project.menu.dto;

import com.example.delivery_project.menu.entity.Menu;
import com.example.delivery_project.menu.entity.MenuDelete;
import com.example.delivery_project.store.entity.Store;
import com.example.delivery_project.store.entity.StoreStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class MenuResponseDto {
    private Long storeId;
    private Long menuId;
    private String name;
    private int price;
    private MenuDelete menuDelete;



    public MenuResponseDto(Long storeId, Long menuId, String name, int price, MenuDelete menuDelete) {
        this.storeId = storeId;
        this.menuId = menuId;
        this.name = name;
        this.price = price;
        this.menuDelete = menuDelete;
    }



    public static MenuResponseDto toDto(Menu menu) {
        return new MenuResponseDto(
                menu.getStore().getId(),
                menu.getMenuId(),
                menu.getName(),
                menu.getPrice(),
                menu.getMenuDelete()
        );
    }

}

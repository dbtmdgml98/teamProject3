package com.example.delivery_project.menu.dto;

import com.example.delivery_project.menu.entity.Menu;
import com.example.delivery_project.store.dto.ReadStoreResponseDto;
import com.example.delivery_project.store.entity.Store;

public class ReadMenuResponseDto {
    private Long storeId;
    private Long menuId;
    private String name;
    private int price;


    public ReadMenuResponseDto(Long storeId, Long menuId, String name, int price) {
        this.storeId = storeId;
        this.menuId = menuId;
        this.name = name;
        this.price = price;
    }


    public static ReadMenuResponseDto toDto(Menu menu) {
        return new ReadMenuResponseDto(
                menu.getStore().getId(),
                menu.getMenuId(),
                menu.getName(),
                menu.getPrice()
        );
    }
}

package com.example.delivery_project.menu.dto;

import com.example.delivery_project.menu.entity.Menu;
import com.example.delivery_project.store.entity.Store;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateMenuRequestDto {

    @NotBlank(message = "메뉴 이름은 필수값 입니다.")
    private String name;
    @NotBlank(message = "메뉴 가격은 필수값 입니다.")
    private int price;

    public CreateMenuRequestDto(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Menu toEntity(Store findStore) {
        return new Menu(
            this.name,
            this.price,
            findStore
        );
    }
}

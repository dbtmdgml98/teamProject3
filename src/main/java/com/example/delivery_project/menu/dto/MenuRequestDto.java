package com.example.delivery_project.menu.dto;

import com.example.delivery_project.menu.entity.MenuDelete;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class MenuRequestDto {

    @NotBlank(message = "메뉴 이름은 필수값 입니다.")
    private String name;
    @NotBlank(message = "메뉴 가격은 필수값 입니다.")
    private int price;
    @NotBlank(message = "메뉴 상태는 필수값 입니다.")
    private MenuDelete menuDelete;

    public MenuRequestDto(String name, int price, MenuDelete menuDelete) {
        this.name = name;
        this.price = price;
        this.menuDelete = menuDelete;
    }
}

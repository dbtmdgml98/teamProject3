package com.example.delivery_project.menu.dto;

import com.example.delivery_project.menu.entity.Menu;
import com.example.delivery_project.menu.entity.MenuDelete;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateMenuRequestDto {
    @NotBlank(message = "메뉴 이름은 필수값 입니다.")
    private String name;
    @NotBlank(message = "메뉴 가격은 필수값 입니다.")
    private int price;
    @NotBlank(message = "메뉴 상태는 필수값 입니다.")
    private MenuDelete menuDelete;

    public CreateMenuRequestDto(String name, int price, MenuDelete menuDelete) {
        this.name = name;
        this.price = price;
        this.menuDelete = menuDelete;
    }
    public Menu toEntity(){
        return new Menu(
                this.name,
                this.price,
                this.menuDelete
        );
    }
}

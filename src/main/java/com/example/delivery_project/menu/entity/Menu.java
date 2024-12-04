package com.example.delivery_project.menu.entity;

import com.example.delivery_project.common.entity.TimeBaseEntity;
import com.example.delivery_project.menu.dto.CreateMenuResponseDto;
import com.example.delivery_project.store.entity.Store;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Menu extends TimeBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Enumerated(value =EnumType.STRING)
    private MenuDelete menuDelete = MenuDelete.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    public Menu(String name, int price, MenuDelete menuDelete, Store store) {
        this.name = name;
        this.price = price;
        this.menuDelete = menuDelete;
        this.store = store;
    }


    public Menu(String name, int price, MenuDelete menuDelete) {
        this.name = name;
        this.price = price;
        this.menuDelete = menuDelete;
    }

    public Menu() {

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


    public void updateMenu(Menu requestDto) {
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.menuDelete = requestDto.getMenuDelete();
    }

    public void updateMenu(String name, int price, MenuDelete menuDelete) {
        this.name = name;
        this.price = price;
        this.menuDelete = menuDelete;
    }

    public void setMenu() {
        this.menuDelete=MenuDelete.DEACTIVATE;
    }
}


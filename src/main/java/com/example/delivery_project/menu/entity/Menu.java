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

    @Enumerated(value = EnumType.ORDINAL)
    private MenuDelete menuDelete = MenuDelete.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    public Menu(Long menuId, String name, int price, MenuDelete menuDelete, Store store) {
        this.menuId = menuId;
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
                menu.getPrice(),
                menu.getName()
        );
    }
}


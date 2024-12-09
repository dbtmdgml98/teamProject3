package com.example.delivery_project.menu.entity;

import com.example.delivery_project.common.entity.TimeBaseEntity;
import com.example.delivery_project.store.entity.Store;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Menu extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Enumerated(value = EnumType.STRING)
    private MenuDelete menuDelete = MenuDelete.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    public Menu(String name, int price, Store store) {
        this.name = name;
        this.price = price;
        this.store = store;
    }


    public Menu(String name, int price, MenuDelete menuDelete) {
        this.name = name;
        this.price = price;
        this.menuDelete = menuDelete;
    }

    public Menu() {

    }

    public void updateMenu(String name, int price, MenuDelete menuDelete) {
        this.name = name;
        this.price = price;
        this.menuDelete = menuDelete;
    }

    public void setMenu() {
        this.menuDelete = MenuDelete.DEACTIVATE;
    }
}


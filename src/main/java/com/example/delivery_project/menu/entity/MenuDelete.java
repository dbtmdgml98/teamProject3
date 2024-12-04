package com.example.delivery_project.menu.entity;

import lombok.Getter;
@Getter
public enum MenuDelete {
    ACTIVE(0),
    DEACTIVATE(1);

    private final int value;

    MenuDelete(int value) {
        this.value = value;
    }
}


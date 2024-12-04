package com.example.delivery_project.menu.entity;

import lombok.Getter;
@Getter
public enum MenuDelete {
    ACTIVE("SELL"),
    DEACTIVATE("CLOSE");

    private final String value;

    MenuDelete(String value) {
        this.value = value;
    }

}


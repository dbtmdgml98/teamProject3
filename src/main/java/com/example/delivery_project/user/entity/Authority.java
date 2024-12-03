package com.example.delivery_project.user.entity;

import lombok.Getter;

@Getter
public enum Authority {
    USER(0), // 일반 유저
    OWNER(1); // 사장

    private final int value;

    Authority(int value) {
        this.value = value;
    }
}

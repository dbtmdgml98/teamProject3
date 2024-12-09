package com.example.delivery_project.user.entity;

import lombok.Getter;

@Getter
public enum Authority {
    USER("user"), // 일반 유저
    OWNER("owner"); // 사장

    private final String authority;

    Authority(String authority) {
        this.authority = authority;
    }
}

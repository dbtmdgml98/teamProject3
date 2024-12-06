package com.example.delivery_project.user.entity;

import lombok.Getter;

@Getter
public enum UserWithdraw {
    ACTIVE("active"), // 가입 상태
    WITHDRAWN("withDraw"); // 탈퇴 상태

    private final String value;

    UserWithdraw(String value) {
        this.value = value;
    }
}

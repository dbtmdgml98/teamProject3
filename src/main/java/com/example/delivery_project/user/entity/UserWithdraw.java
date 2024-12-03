package com.example.delivery_project.user.entity;

import lombok.Getter;

@Getter
public enum UserWithdraw {
    ACTIVE(0), // 가입 상태
    WITHDRAWN(1); // 탈퇴 상태

    private final int value;

    UserWithdraw(int value) {
        this.value = value;
    }
}

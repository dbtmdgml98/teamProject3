package com.example.delivery_project.store.entity;

public enum StoreStatus {

    OPEN(0),    // 가게 영업 상태
    CLOSE(1);   // 가게 폐업 상태

    private final int value;

    StoreStatus(int value) {
        this.value = value;
    }
}

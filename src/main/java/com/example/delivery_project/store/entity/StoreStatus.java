package com.example.delivery_project.store.entity;

public enum StoreStatus {

    OPEN("open"),    // 가게 영업 상태
    CLOSE("close");   // 가게 폐업 상태

    private final String value;

    StoreStatus(String value) {
        this.value = value;
    }
}

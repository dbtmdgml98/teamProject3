package com.example.delivery_project.store.dto;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class StoreRequestDto {

    private final String name;
    private final LocalTime openTime;
    private final LocalTime closeTime;
    private final Integer minimumOrderPrice;

    public StoreRequestDto(String name, LocalTime openTime, LocalTime closeTime, Integer minimumOrderPrice) {
        this.name = name;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.minimumOrderPrice = minimumOrderPrice;
    }
}

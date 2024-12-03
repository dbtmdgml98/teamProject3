package com.example.delivery_project.store.dto;

import com.example.delivery_project.store.entity.Store;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class StoreResponseDto {

    private final Long storeId;
    private final String name;
    private final Integer storeStatus;
    private final LocalTime openTime;
    private final LocalTime closeTime;
    private final Integer minimumOrderPrice;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public StoreResponseDto(Long storeId, String name, Integer storeStatus, LocalTime openTime, LocalTime closeTime, Integer minimumOrderPrice, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.storeId = storeId;
        this.name = name;
        this.storeStatus = storeStatus;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.minimumOrderPrice = minimumOrderPrice;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public StoreResponseDto(Store store) {
        this.storeId = store.getId();
        this.name = store.getName();
        this.storeStatus = store.getStoreStatus();
        this.openTime = store.getOpenTime();
        this.closeTime = store.getCloseTime();
        this.minimumOrderPrice = store.getMinimumOrderPrice();
        this.createdAt = store.getCreatedAt();
        this.modifiedAt = store.getModifiedAt();
    }


}

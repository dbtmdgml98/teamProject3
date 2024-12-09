package com.example.delivery_project.store.dto;

import com.example.delivery_project.store.entity.Store;
import com.example.delivery_project.store.entity.StoreStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class ReadAllStoreResponseDto {

    private final Long storeId;
    private final String name;
    private final StoreStatus storeStatus;
    private final LocalTime openTime;
    private final LocalTime closeTime;
    private final Integer minimumOrderPrice;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ReadAllStoreResponseDto(Long storeId, String name, StoreStatus storeStatus, LocalTime openTime, LocalTime closeTime, Integer minimumOrderPrice, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.storeId = storeId;
        this.name = name;
        this.storeStatus = storeStatus;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.minimumOrderPrice = minimumOrderPrice;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static ReadAllStoreResponseDto toDto(Store store) {
        return new ReadAllStoreResponseDto(
                store.getId(),
                store.getName(),
                store.getStoreStatus(),
                store.getOpenTime(),
                store.getCloseTime(),
                store.getMinimumOrderPrice(),
                store.getCreatedAt(),
                store.getModifiedAt()
        );
    }
}

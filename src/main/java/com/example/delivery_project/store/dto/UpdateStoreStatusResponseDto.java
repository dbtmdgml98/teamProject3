package com.example.delivery_project.store.dto;

import com.example.delivery_project.store.entity.StoreStatus;
import lombok.Getter;

@Getter
public class UpdateStoreStatusResponseDto {

    private final Long storeId;
    private final String name;
    private final StoreStatus storeStatus;

    public UpdateStoreStatusResponseDto(Long storeId, String name, StoreStatus storeStatus) {
        this.storeId = storeId;
        this.name = name;
        this.storeStatus = storeStatus;
    }
}

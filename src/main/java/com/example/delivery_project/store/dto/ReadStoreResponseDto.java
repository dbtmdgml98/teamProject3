package com.example.delivery_project.store.dto;

import com.example.delivery_project.menu.dto.MenuResponseDto;
import com.example.delivery_project.store.entity.Store;
import com.example.delivery_project.store.entity.StoreStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ReadStoreResponseDto {

    private final Long storeId;
    private final String name;
    private final StoreStatus storeStatus;
    private final LocalTime openTime;
    private final LocalTime closeTime;
    private final Integer minimumOrderPrice;
    private final List<MenuResponseDto> menus;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ReadStoreResponseDto(Long storeId, String name, StoreStatus storeStatus, LocalTime openTime, LocalTime closeTime, Integer minimumOrderPrice, List<MenuResponseDto> menus, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.storeId = storeId;
        this.name = name;
        this.storeStatus = storeStatus;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.minimumOrderPrice = minimumOrderPrice;
        this.menus = menus;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    // Store 타입을 ReadStoreResponseDto 타입 형태로 변환
    public static ReadStoreResponseDto toDto(Store store) {
        return new ReadStoreResponseDto(
                store.getId(),
                store.getName(),
                store.getStoreStatus(),
                store.getOpenTime(),
                store.getCloseTime(),
                store.getMinimumOrderPrice(),
                store.getMenus().stream()
                        .map(MenuResponseDto::toDto)
                        .collect(Collectors.toList()),
                store.getCreatedAt(),
                store.getModifiedAt()
        );
    }
}

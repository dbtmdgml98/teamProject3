package com.example.delivery_project.store.entity;

import com.example.delivery_project.common.entity.TimeBaseEntity;
import com.example.delivery_project.store.dto.StoreRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalTime;

@Entity
@Getter
@Table(name = "store")
public class Store extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(nullable = false)
    private StoreStatus storeStatus;

    @Column(nullable = false)
    private LocalTime openTime;

    @Column(nullable = false)
    private LocalTime closeTime;

    @Column(nullable = false)
    private Integer minimumOrderPrice;

    public Store() {}

    public Store(String name, LocalTime openTime, LocalTime closeTime, Integer minimumOrderPrice) {
        this.name = name;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.minimumOrderPrice = minimumOrderPrice;
        this.storeStatus = StoreStatus.OPEN;
    }

    public void closeStore() {
        this.storeStatus = StoreStatus.CLOSE;
    }

    public void updateStore(StoreRequestDto storeRequestDto) {
        this.name = storeRequestDto.getName();
        this.openTime = storeRequestDto.getOpenTime();
        this.closeTime = storeRequestDto.getCloseTime();
        this.minimumOrderPrice = storeRequestDto.getMinimumOrderPrice();
    }
}

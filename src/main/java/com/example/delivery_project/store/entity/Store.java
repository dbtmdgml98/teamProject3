package com.example.delivery_project.store.entity;

import com.example.delivery_project.common.entity.TimeBaseEntity;
import com.example.delivery_project.menu.entity.Menu;
import com.example.delivery_project.store.dto.StoreRequestDto;
import com.example.delivery_project.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "store")
public class Store extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private StoreStatus storeStatus;

    @Column(nullable = false)
    private LocalTime openTime;

    @Column(nullable = false)
    private LocalTime closeTime;

    @Column(nullable = false)
    private Integer minimumOrderPrice;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "store")
    private List<Menu> menus = new ArrayList<>();

    public Store() {}

    public Store(String name, LocalTime openTime, LocalTime closeTime, Integer minimumOrderPrice, User user) {
        this.name = name;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.minimumOrderPrice = minimumOrderPrice;
        this.user = user;
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

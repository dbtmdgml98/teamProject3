package com.example.delivery_project.order.entity;

import com.example.delivery_project.common.entity.TimeBaseEntity;
import com.example.delivery_project.menu.entity.Menu;
import com.example.delivery_project.order.dto.OrderRequestDto;
import com.example.delivery_project.order.dto.UpdateOrderRequestDto;
import com.example.delivery_project.store.entity.Store;
import com.example.delivery_project.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Table(name = "orders")
public class Order extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    public Order() {}

    public Order(User user, Menu menu, Store store) {
        this.user = user;
        this.menu = menu;
        this.store = store;
    }

    public void updateOrderStatus(UpdateOrderRequestDto orderRequestDto) {
        this.orderStatus = orderRequestDto.getOrderStatus();
    }

}

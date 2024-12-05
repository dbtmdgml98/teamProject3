package com.example.delivery_project.order.entity;

import com.example.delivery_project.common.entity.TimeBaseEntity;
import com.example.delivery_project.menu.entity.Menu;
import com.example.delivery_project.order.dto.OrderRequestDto;
import com.example.delivery_project.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;



@Entity
@Getter
@Table(name = "`order`")
public class Order extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "menuId")
    private Menu menu;



    public Order() {}

    public Order(User user, Menu menu) {
        this.user = user;
        this.menu = menu;
    }

    public void updateOrderStatus(OrderRequestDto orderRequestDto) {
        this.orderStatus = orderRequestDto.getOrderStatus();
    }

}

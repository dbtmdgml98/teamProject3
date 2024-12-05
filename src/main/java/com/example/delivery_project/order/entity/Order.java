package com.example.delivery_project.order.entity;

import com.example.delivery_project.common.entity.TimeBaseEntity;
import com.example.delivery_project.menu.entity.Menu;
import com.example.delivery_project.order.dto.OrderRequestDto;
import com.example.delivery_project.order.dto.UpdateOrderRequestDto;
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

    public void updateOrderStatus(UpdateOrderRequestDto orderRequestDto) {
        this.orderStatus = orderRequestDto.getOrderStatus();
    }

}

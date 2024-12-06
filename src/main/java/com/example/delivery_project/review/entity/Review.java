package com.example.delivery_project.review.entity;

import com.example.delivery_project.common.entity.TimeBaseEntity;
import com.example.delivery_project.order.entity.Order;
import com.example.delivery_project.review.dto.ReadReviewResponseDto;
import com.example.delivery_project.review.dto.ReviewResponseDto;
import com.example.delivery_project.store.entity.Store;
import com.example.delivery_project.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Review extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Column(nullable = false)
    private Integer starPoint;

    @Column(nullable = false)
    private String content;

    public Review() {

    }


    public Review(Long id, Order order, Store store, User user, Integer starPoint, String content) {
        Id = id;
        this.order = order;
        this.store = store;
        this.user = user;
        this.starPoint = starPoint;
        this.content = content;
    }

    public Review(Order order, User user, Store store, Integer starPoint, String content) {
        this.order = order;
        this.user = user;
        this.store = store;
        this.starPoint = starPoint;
        this.content = content;
    }


}

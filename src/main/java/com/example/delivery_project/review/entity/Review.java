package com.example.delivery_project.review.entity;

import com.example.delivery_project.common.entity.TimeBaseEntity;
import com.example.delivery_project.order.entity.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "review")
public class Review extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private Integer starPoint;

    @Column(nullable = false)
    private String content;

    public Review() {}

    public Review(Order order, Integer starPoint, String content) {
        this.order = order;
        this.starPoint = starPoint;
        this.content = content;
    }
}

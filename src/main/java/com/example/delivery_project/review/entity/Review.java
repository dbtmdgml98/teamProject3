package com.example.delivery_project.review.entity;

import com.example.delivery_project.common.entity.TimeBaseEntity;
import com.example.delivery_project.order.entity.Order;
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


    @Column(nullable = false)
    private Integer starPoint;

    @Column(nullable = false)
    private String content;

    public Review() {

    }

    public Review(Long id, Order order, Integer starPoint, String content) {
        Id = id;
        this.order = order;
        this.starPoint = starPoint;
        this.content = content;
    }

    public Review(Long id, Order order, Integer starPoint, String content, Store store) {
        Id = id;
        this.order = order;
        this.starPoint = starPoint;
        this.content = content;
        this.store = store;
    }

    public Review(Order order, Integer starPoint, String content) {
        this.order = order;
        this.starPoint = starPoint;
        this.content = content;
    }

    public static ReviewResponseDto toDto(Review review) {
        return new ReviewResponseDto(
                review.getId(),
                review.getOrder().getId(),
                review.getStore().getId(),
                review.getStarPoint(),
                review.getContent(),
                review.getCreatedAt()
        );
    }

}

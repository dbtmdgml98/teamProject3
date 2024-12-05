package com.example.delivery_project.review.dto;

import com.example.delivery_project.menu.dto.ReadMenuResponseDto;
import com.example.delivery_project.menu.entity.Menu;
import com.example.delivery_project.review.entity.Review;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {
    private Long Id;
    private Long orderId;
    private Long storeId;
    private Integer starPoint;
    private String content;
    private LocalDateTime createdAt;

    public ReviewResponseDto(Long id, Long orderId, Long storeId, Integer starPoint, String content, LocalDateTime createdAt) {
        this.Id = id;
        this.orderId = orderId;
        this.storeId = storeId;
        this.starPoint = starPoint;
        this.content = content;
        this.createdAt = createdAt;
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

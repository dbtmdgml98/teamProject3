package com.example.delivery_project.review.dto;

import com.example.delivery_project.review.entity.Review;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ReadReviewResponseDto {

    private final Long Id;
    private final Long orderId;
    private final Long storeId;
    private final Long userId;
    private final Integer starPoint;
    private final String content;
    private final LocalDateTime createdAt;

    public ReadReviewResponseDto(Long id, Long orderId, Long storeId, Long userId, Integer starPoint, String content, LocalDateTime createdAt) {
        Id = id;
        this.orderId = orderId;
        this.storeId = storeId;
        this.userId = userId;
        this.starPoint = starPoint;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static ReadReviewResponseDto toDto(Review review) {
        return new ReadReviewResponseDto(
                review.getId(),
                review.getOrder().getId(),
                review.getStore().getId(),
                review.getUser().getId(),
                review.getStarPoint(),
                review.getContent(),
                review.getCreatedAt()
        );
    }
}
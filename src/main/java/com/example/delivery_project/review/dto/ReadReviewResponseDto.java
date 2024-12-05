package com.example.delivery_project.review.dto;

import com.example.delivery_project.menu.dto.ReadMenuResponseDto;
import com.example.delivery_project.menu.entity.Menu;
import com.example.delivery_project.review.entity.Review;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ReadReviewResponseDto {

    private final Long Id;
    private final Long orderId;
    private final Long storeId;
    private final Integer starPoint;
    private final String content;
    private final LocalDateTime createdAt;

    public ReadReviewResponseDto(Long id, Long orderId, Long storeId, Integer starPoint, String content, LocalDateTime createdAt) {
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
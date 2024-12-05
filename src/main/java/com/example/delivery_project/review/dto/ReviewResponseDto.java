package com.example.delivery_project.review.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {

    private final Long reviewId;
    private final Long orderId;
    private final String content;
    private final Integer starPoint;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ReviewResponseDto(Long reviewId, Long orderId, String content, Integer starPoint, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.reviewId = reviewId;
        this.orderId = orderId;
        this.content = content;
        this.starPoint = starPoint;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}

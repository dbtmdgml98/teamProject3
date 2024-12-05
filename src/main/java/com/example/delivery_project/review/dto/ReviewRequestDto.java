package com.example.delivery_project.review.dto;

import lombok.Getter;

@Getter
public class ReviewRequestDto {

    private final Long orderId;
    private final Integer starPoint;
    private final String content;

    public ReviewRequestDto(Long orderId, Integer starPoint, String content) {
        this.orderId = orderId;
        this.starPoint = starPoint;
        this.content = content;
    }
}

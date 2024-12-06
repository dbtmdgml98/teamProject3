package com.example.delivery_project.review.dto;

import lombok.Getter;

@Getter
public class ReviewRequestDto {

    private final Integer starPoint;
    private final String content;

    public ReviewRequestDto(Integer starPoint, String content) {
        this.starPoint = starPoint;
        this.content = content;
    }
}

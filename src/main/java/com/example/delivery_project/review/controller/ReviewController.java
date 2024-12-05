package com.example.delivery_project.review.controller;

import com.example.delivery_project.review.dto.ReviewRequestDto;
import com.example.delivery_project.review.dto.ReviewResponseDto;
import com.example.delivery_project.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stores/{storeId}/comments")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponseDto> createReview(@RequestBody ReviewRequestDto reviewRequestDto) {

        ReviewResponseDto createdReview = reviewService.createReview(reviewRequestDto);

        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }
}

package com.example.delivery_project.review.controller;

import com.example.delivery_project.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stores/{storeId}/comments")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
}

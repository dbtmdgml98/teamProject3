package com.example.delivery_project.review.service;

import com.example.delivery_project.review.controller.ReviewController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewController reviewController;

}

package com.example.delivery_project.review.service;

import com.example.delivery_project.order.entity.Order;
import com.example.delivery_project.order.repository.OrderRepository;
import com.example.delivery_project.review.dto.ReviewRequestDto;
import com.example.delivery_project.review.dto.ReviewResponseDto;
import com.example.delivery_project.review.entity.Review;
import com.example.delivery_project.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;

    public ReviewResponseDto createReview(ReviewRequestDto reviewRequestDto) {

        Order foundOrder = orderRepository.findByIdOrElseThrow(reviewRequestDto.getOrderId());

        Review review = new Review(foundOrder, reviewRequestDto.getStarPoint(), reviewRequestDto.getContent());

        Review savedReview = reviewRepository.save(review);

        return new ReviewResponseDto(savedReview.getId(), savedReview.getOrder().getId(), savedReview.getContent(), savedReview.getStarPoint(), savedReview.getCreatedAt(), savedReview.getModifiedAt());
    }
}

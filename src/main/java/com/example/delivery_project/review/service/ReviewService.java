package com.example.delivery_project.review.service;

import com.example.delivery_project.order.entity.Order;
import com.example.delivery_project.order.entity.OrderStatus;
import com.example.delivery_project.order.repository.OrderRepository;
import com.example.delivery_project.review.dto.ReviewRequestDto;
import com.example.delivery_project.review.dto.ReviewResponseDto;
import com.example.delivery_project.review.entity.Review;
import com.example.delivery_project.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;

    public ReviewResponseDto createReview(Long orderId, ReviewRequestDto reviewRequestDto) {

        Order foundOrder = orderRepository.findByIdOrElseThrow(orderId);

        // 배달 완료 되지 않은 주문일 경우
        if (!foundOrder.getOrderStatus().equals(OrderStatus.DELIVERY_FINISHED)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "배달 완료 되지 않은 주문은 리뷰를 작성할 수 없습니다.");
        }

        Review review = new Review(foundOrder, reviewRequestDto.getStarPoint(), reviewRequestDto.getContent());

        Review savedReview = reviewRepository.save(review);

        return new ReviewResponseDto(savedReview.getId(), savedReview.getOrder().getId(), savedReview.getContent(), savedReview.getStarPoint(), savedReview.getCreatedAt(), savedReview.getModifiedAt());
    }
}

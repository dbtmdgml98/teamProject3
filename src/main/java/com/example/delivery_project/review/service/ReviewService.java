package com.example.delivery_project.review.service;

import com.example.delivery_project.order.entity.Order;
import com.example.delivery_project.order.entity.OrderStatus;
import com.example.delivery_project.order.repository.OrderRepository;
import com.example.delivery_project.review.dto.ReviewRequestDto;
import com.example.delivery_project.review.dto.ReviewResponseDto;
import com.example.delivery_project.review.entity.Review;
import com.example.delivery_project.review.dto.ReadReviewResponseDto;
import com.example.delivery_project.review.repository.ReviewRepository;
import com.example.delivery_project.store.entity.Store;
import com.example.delivery_project.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final StoreService storeService;

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

    public Page<ReadReviewResponseDto> getPostsPage(int page, Long storeId) {
        Store findStore = storeService.findById(storeId);
        Pageable pageable = PageRequest.of(page, 10, Sort.by("createdAt").descending());

        return reviewRepository.findAllByStoreId(findStore.getId(),pageable).map(ReadReviewResponseDto::toDto);
    }


}

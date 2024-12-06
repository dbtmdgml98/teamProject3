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
import com.example.delivery_project.store.repository.StoreRepository;
import com.example.delivery_project.store.service.StoreService;
import com.example.delivery_project.user.entity.User;
import com.example.delivery_project.user.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;

    public ReviewResponseDto createReview(
        Long orderId, Long userId,
        ReviewRequestDto reviewRequestDto
    ) {

        Order findOrder = orderRepository.findByIdOrElseThrow(orderId);
        User findUser = userRepository.findByIdOrElseThrow(userId);
        Store findStore = findOrder.getMenu().getStore();

        // 배달 완료 되지 않은 주문일 경우
        if (!findOrder.getOrderStatus().equals(OrderStatus.DELIVERY_FINISHED)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                "배달 완료 되지 않은 주문은 리뷰를 작성할 수 없습니다.");
        }

        Review review = new Review(findOrder, findUser, findStore, reviewRequestDto.getStarPoint(),
            reviewRequestDto.getContent());

        Review savedReview = reviewRepository.save(review);

        return new ReviewResponseDto(savedReview.getId(), savedReview.getOrder().getId(),
            savedReview.getContent(), savedReview.getStarPoint(), savedReview.getCreatedAt(),
            savedReview.getModifiedAt());
    }

    public Page<ReadReviewResponseDto> getPostsPage(int page, Long storeId, Long userId) {
        Store findStore = storeRepository.findByIdOrElseThrow(storeId);
        Pageable pageable = PageRequest.of(page, 10, Sort.by("createdAt").descending());

        return reviewRepository.findAllByStoreIdAndUserIdNot(
            findStore.getId(), userId, pageable
            ).map(ReadReviewResponseDto::toDto);
    }

    public Page<ReadReviewResponseDto> getStarPointPage(int page, Long storeId, Long userId,
        Long minStar, Long maxStar) {
        Store findStore = storeRepository.findByIdOrElseThrow(storeId);
        Pageable pageable = PageRequest.of(page, 10, Sort.by("createdAt").descending());

        return reviewRepository.findAllByStoreIdAndUserIdNotAndStarPointBetween(
            findStore.getId(),
            userId, minStar, maxStar, pageable
        ).map(ReadReviewResponseDto::toDto);
    }

}

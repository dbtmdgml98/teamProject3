package com.example.delivery_project.review.service;

import com.example.delivery_project.review.dto.ReviewResponseDto;
import com.example.delivery_project.review.repository.ReviewRepository;
import com.example.delivery_project.store.entity.Store;
import com.example.delivery_project.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreService storeService;

    public Page<ReviewResponseDto> getPostsPage(int page, Long storeId) {
        Store findStore = storeService.findById(storeId);
        Pageable pageable = PageRequest.of(page, 10, Sort.by("createdAt").descending());

        return reviewRepository.findAllByStoreId(findStore.getId(),pageable).map(ReviewResponseDto::toDto);
    }

}

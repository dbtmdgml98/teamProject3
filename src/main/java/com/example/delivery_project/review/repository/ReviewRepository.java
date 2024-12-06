package com.example.delivery_project.review.repository;


import com.example.delivery_project.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findAllByStoreId(Long id,Long userId,Pageable pageable);


    Page<Review> findAllByStoreIdAndUserIdNot(Long id,Long userId,Pageable pageable);

}

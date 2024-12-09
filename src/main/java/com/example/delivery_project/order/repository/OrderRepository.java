package com.example.delivery_project.order.repository;

import com.example.delivery_project.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    default Order findByIdOrElseThrow(Long orderId) {
        return findById(orderId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
    }
}

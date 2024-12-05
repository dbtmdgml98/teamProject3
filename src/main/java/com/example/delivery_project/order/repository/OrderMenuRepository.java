package com.example.delivery_project.order.repository;

import com.example.delivery_project.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;


@Repository
public interface OrderMenuRepository extends JpaRepository<Menu, Long> {

    default Menu findByIdOrElseThrow(Long menuId) {
        return findById(menuId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
    }

}
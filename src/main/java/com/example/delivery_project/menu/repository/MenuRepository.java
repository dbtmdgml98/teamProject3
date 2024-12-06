package com.example.delivery_project.menu.repository;

import com.example.delivery_project.menu.entity.Menu;
import com.example.delivery_project.menu.entity.MenuDelete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;


@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    Page<Menu> findAllByStoreIdAndMenuDelete(Long id, MenuDelete menuDelete, Pageable pageable);

    Menu findMenuByMenuId(Long id);

    default Menu findByIdOrElseThrow(Long userId) {
        return findById(userId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
    }

}
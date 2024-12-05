package com.example.delivery_project.menu.repository;

import com.example.delivery_project.menu.dto.ReadMenuResponseDto;
import com.example.delivery_project.menu.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    Page<Menu> findAllByStoreId(Long id, Pageable pageable);

    Menu findMenuByMenuId(Long id);
}

package com.example.delivery_project.menu.repository;

import com.example.delivery_project.menu.entity.Menu;
import com.example.delivery_project.store.entity.Store;
import com.example.delivery_project.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    Page<Menu> findAllByStoreId(Long id, Pageable pageable);

}

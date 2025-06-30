package com.example.delivery_project.store.repository;

import com.example.delivery_project.menu.entity.Menu;
import com.example.delivery_project.store.entity.Store;
import com.example.delivery_project.store.entity.StoreStatus;
import com.example.delivery_project.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    Store findByName(String name);

    Long countByUserAndStoreStatus(User user, StoreStatus storeStatus);

    Page<Store> findAllByStoreStatus(Pageable pageable, StoreStatus storeStatus);

    Page<Store> findAllByNameIsContaining(Pageable pageable, String storeName);

    Page<Store> findAllByStoreStatusAndNameIsContaining(Pageable pageable, StoreStatus storeStatus, String storeName);

    Store findStoreById(Long id);

    default Store findByIdOrElseThrow(Long userId) {
        return findById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
    }
}

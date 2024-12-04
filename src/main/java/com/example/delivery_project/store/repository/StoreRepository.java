package com.example.delivery_project.store.repository;

import com.example.delivery_project.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Menu, Long> {

}

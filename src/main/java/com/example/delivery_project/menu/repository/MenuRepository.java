package com.example.delivery_project.menu.repository;

import com.example.delivery_project.menu.entity.Menu;
import com.example.delivery_project.menu.entity.MenuDelete;
import com.example.delivery_project.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;


@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    Page<Menu> findAllByStoreIdAndMenuDelete(Long id,MenuDelete menuDelete, Pageable pageable);

    Menu findMenuByMenuId(Long id);

    default Menu findByIdOrElseThrow(Long userId) {
        return findById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
    }

//    @Query("SELECT new com.example.delivery_project.menu.dto.MenuResponseDto(" +
//            "s.id, m.menuId, m.name, m.price, m.menuDelete ) " +
//            "FROM Menu m join Store s on m.store.id = s.id " +
//            "WHERE m.menuDelete = 'ACTIVE' ")
//    List<MenuResponseDto> getStatsByMenu(Long userId,Pageable pageable);
}
package com.example.delivery_project.menu.service;

import com.example.delivery_project.menu.dto.CreateMenuRequestDto;
import com.example.delivery_project.menu.dto.CreateMenuResponseDto;
import com.example.delivery_project.menu.entity.Menu;
import com.example.delivery_project.menu.repository.MenuRepository;
import com.example.delivery_project.store.dto.ReadStoreResponseDto;
import com.example.delivery_project.store.entity.Store;
import com.example.delivery_project.store.service.StoreService;
import com.example.delivery_project.user.dto.CreateUserRequestDto;
import com.example.delivery_project.user.dto.LoginRequestDto;
import com.example.delivery_project.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public Menu findById(Long userId) {
        return menuRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("메뉴 를 찾을 수 없습니다."));
    }
    @Transactional
    public CreateMenuResponseDto save(CreateMenuRequestDto dto, User loginUser, Store findStore){

        if(loginUser.getAuthority().equals(0)){
            throw new IllegalArgumentException("주인이 아닙니다.");
        }

        if(!findStore.getUser().getId().equals(loginUser.getId())){
            throw new IllegalArgumentException("가게주인이 아닙니다.");
        }

        Menu savedMenu = menuRepository.save(dto.toEntity(findStore));
        return Menu.toDto(savedMenu);
    }

}

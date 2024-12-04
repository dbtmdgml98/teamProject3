package com.example.delivery_project.menu.service;

import com.example.delivery_project.menu.dto.CreateMenuRequestDto;
import com.example.delivery_project.menu.dto.CreateMenuResponseDto;
import com.example.delivery_project.menu.entity.Menu;
import com.example.delivery_project.menu.repository.MenuRepository;
import com.example.delivery_project.store.service.StoreService;
import com.example.delivery_project.user.dto.CreateUserRequestDto;
import com.example.delivery_project.user.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final StoreService storeService;

    public List<CreateMenuResponseDto> findByIdOrElseThrow(Long userId) {

        return null;
    }
    @Transactional
    public CreateMenuResponseDto save(CreateMenuRequestDto dto, CreateUserRequestDto loginUser){


        if(loginUser.getAuthority().equals(0)){
            throw new IllegalArgumentException("주인이 아닙니다.");
        }
        Menu savedMenu = menuRepository.save(dto.toEntity());
        return Menu.toDto(savedMenu);
    }

}

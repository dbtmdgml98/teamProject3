package com.example.delivery_project.menu.service;

import com.example.delivery_project.menu.dto.CreateMenuRequestDto;
import com.example.delivery_project.menu.dto.CreateMenuResponseDto;
import com.example.delivery_project.menu.entity.Menu;
import com.example.delivery_project.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public List<CreateMenuResponseDto> findByIdOrElseThrow(Long userId) {

        return null;
    }
    @Transactional
    public CreateMenuResponseDto save(CreateMenuRequestDto dto){
        Menu savedMenu = menuRepository.save(dto.toEntity());
        return Menu.toDto(savedMenu);
    }

}

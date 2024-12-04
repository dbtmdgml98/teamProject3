package com.example.delivery_project.menu.service;

import com.example.delivery_project.menu.dto.CreateMenuRequestDto;
import com.example.delivery_project.menu.dto.CreateMenuResponseDto;
import com.example.delivery_project.menu.entity.Menu;
import com.example.delivery_project.menu.entity.MenuDelete;
import com.example.delivery_project.menu.repository.MenuRepository;
import com.example.delivery_project.store.entity.Store;
import com.example.delivery_project.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

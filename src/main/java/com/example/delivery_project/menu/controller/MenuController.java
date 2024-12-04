package com.example.delivery_project.menu.controller;

import com.example.delivery_project.menu.dto.CreateMenuRequestDto;
import com.example.delivery_project.menu.dto.CreateMenuResponseDto;
import com.example.delivery_project.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores/owners")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;


    @PostMapping
    public ResponseEntity<CreateMenuResponseDto> save(
            @RequestBody CreateMenuRequestDto requestDto) {
        CreateMenuResponseDto save = menuService.save(requestDto);

        return new ResponseEntity<>(save, HttpStatus.CREATED);

    }

}

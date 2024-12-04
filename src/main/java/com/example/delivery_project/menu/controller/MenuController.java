package com.example.delivery_project.menu.controller;

import com.example.delivery_project.menu.dto.CreateMenuRequestDto;
import com.example.delivery_project.menu.dto.CreateMenuResponseDto;
import com.example.delivery_project.menu.service.MenuService;
import com.example.delivery_project.store.dto.StoreResponseDto;
import com.example.delivery_project.store.service.StoreService;
import com.example.delivery_project.user.dto.CreateUserRequestDto;
import com.example.delivery_project.user.dto.LoginRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners/stores/")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;



    @PostMapping("/{id}/menus")
    public ResponseEntity<CreateMenuResponseDto> save(
            @PathVariable Long id,
            @RequestBody CreateMenuRequestDto requestDto,
            HttpServletRequest request) {

//        StoreResponseDto storeResponseDto = storeService.f
        HttpSession session = request.getSession(false);
        CreateUserRequestDto loginUser =(CreateUserRequestDto) session.getAttribute("userAuthority");

        CreateMenuResponseDto save = menuService.save(requestDto,loginUser);

        return new ResponseEntity<>(save, HttpStatus.CREATED);

    }

}

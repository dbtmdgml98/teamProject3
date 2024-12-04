package com.example.delivery_project.menu.controller;

import com.example.delivery_project.menu.dto.CreateMenuRequestDto;
import com.example.delivery_project.menu.dto.CreateMenuResponseDto;
import com.example.delivery_project.menu.dto.ReadMenuResponseDto;
import com.example.delivery_project.menu.service.MenuService;
import com.example.delivery_project.store.dto.ReadStoreResponseDto;
import com.example.delivery_project.store.dto.StoreResponseDto;
import com.example.delivery_project.store.entity.Store;
import com.example.delivery_project.store.service.StoreService;
import com.example.delivery_project.user.dto.CreateUserRequestDto;
import com.example.delivery_project.user.dto.LoginRequestDto;
import com.example.delivery_project.user.entity.User;
import com.example.delivery_project.user.service.UserService;
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
    private final StoreService storeService;
    private final UserService userService;

    @GetMapping("/{storeId}/menus")
    public ResponseEntity<ReadMenuResponseDto> findById(@PathVariable(name = "storeId") Long storeId) {

        Store findStore = storeService.findById(storeId);
        ReadMenuResponseDto foundStore = ReadMenuResponseDto.toDto(menuService.findById(storeId));

        return new ResponseEntity<>(foundStore, HttpStatus.OK);
    }

    @PostMapping("/{storeId}/menus")
    public ResponseEntity<CreateMenuResponseDto> save(
            @PathVariable Long storeId,
            @RequestBody CreateMenuRequestDto requestDto,
            HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Store findStore = storeService.findById(storeId);


        Long userId = (Long) session.getAttribute("userId");
        User findUser = userService.findById(userId);

        CreateMenuResponseDto savedMenu = menuService.save(requestDto,findUser,findStore);

        return new ResponseEntity<>(savedMenu, HttpStatus.CREATED);

    }

}

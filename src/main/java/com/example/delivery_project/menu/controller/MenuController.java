package com.example.delivery_project.menu.controller;

import com.example.delivery_project.menu.dto.*;
import com.example.delivery_project.menu.service.MenuService;
import com.example.delivery_project.store.entity.Store;
import com.example.delivery_project.store.service.StoreService;
import com.example.delivery_project.user.entity.User;
import com.example.delivery_project.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owners/stores/")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;
    private final StoreService storeService;
    private final UserService userService;

    @GetMapping("/{storeId}/menus")
    public Page<ReadMenuResponseDto> findByAll(
            @PathVariable(name = "storeId") Long storeId,
            @RequestParam(required = false, defaultValue = "0", value = "page") int page) {

//        Store findStore = storeService.findById(storeId);
//        ReadMenuResponseDto findMenu = menuService.findMenuById(findStore.getId());

        return menuService.getPostsPage(page,storeId);
    }


    @PostMapping("/{storeId}/menus")
    public ResponseEntity<CreateMenuResponseDto> save(
            @PathVariable Long storeId,
            @RequestBody CreateMenuRequestDto requestDto,
            HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        Long userId = (Long) session.getAttribute("userId");

        CreateMenuResponseDto savedMenu = menuService.save(storeId,userId,requestDto);

        return new ResponseEntity<>(savedMenu, HttpStatus.CREATED);

    }
    @PatchMapping("/{storeId}/menus")
    public ResponseEntity<CreateMenuResponseDto> update(
            @PathVariable Long storeId,
            @RequestBody UpdateMenuStatusRequestDto requestDto,
            HttpServletRequest request){

        HttpSession session = request.getSession(false);

        Long userId = (Long) session.getAttribute("userId");

        CreateMenuResponseDto updateMenu = menuService.updateMenu(storeId,userId,requestDto);


        return new ResponseEntity<>(updateMenu, HttpStatus.CREATED);

    }
    @DeleteMapping("/{storeId}/menus")
    public ResponseEntity<Void> delete(
            @PathVariable Long storeId,
            @RequestBody MenuRequestDto requestDto,
            HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("userId");
        menuService.deleteMenu(storeId,userId,requestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}

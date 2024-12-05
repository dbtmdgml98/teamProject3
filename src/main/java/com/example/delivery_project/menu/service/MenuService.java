package com.example.delivery_project.menu.service;

import com.example.delivery_project.menu.dto.*;
import com.example.delivery_project.menu.entity.Menu;
import com.example.delivery_project.menu.entity.MenuDelete;
import com.example.delivery_project.menu.repository.MenuRepository;
import com.example.delivery_project.store.entity.Store;
import com.example.delivery_project.store.service.StoreService;
import com.example.delivery_project.user.entity.User;
import com.example.delivery_project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.delivery_project.user.entity.Authority.USER;


@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final StoreService storeService;
    private final UserService userService;

    public Menu findById(Long userId) {
        return menuRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("메뉴 를 찾을 수 없습니다."));
    }

    public ReadMenuResponseDto findMenuById(Long id) {

        Menu findMenu = findById(id);
        return ReadMenuResponseDto.toDto(findMenu);
    }
    @Transactional
    public CreateMenuResponseDto save(Long storeId, Long userId, CreateMenuRequestDto requestDto){
        Store findStore = storeService.findById(storeId);
        User loginUser = userService.findById(userId);

        if(loginUser.getAuthority().equals(USER)){
            throw new IllegalArgumentException("주인이 아닙니다.");
        }

        if(!findStore.getUser().getId().equals(loginUser.getId())){
            throw new IllegalArgumentException("가게주인이 아닙니다.");
        }


        Menu savedMenu = menuRepository.save(requestDto.toEntity(findStore));
        return Menu.toDto(savedMenu);
    }

    public Page<ReadMenuResponseDto> getPostsPage(int page, Long storeId) {
        Store findStore = storeService.findById(storeId);
        ReadMenuResponseDto findMenu = ReadMenuResponseDto.toDto(menuRepository.findMenuByMenuId(findStore.getId()));

        Pageable pageable = PageRequest.of(page, 10,Sort.by("createdAt").descending());

        return menuRepository.findAllByStoreIdAndMenuDelete(findMenu.getStoreId(), MenuDelete.ACTIVE,pageable).map(ReadMenuResponseDto::toDto);
    }
    @Transactional
    public CreateMenuResponseDto updateMenu(Long storeId, Long userId, UpdateMenuStatusRequestDto requestDto) {

        Store findStore = storeService.findById(storeId);
        User loginUser = userService.findById(userId);


        if(loginUser.getAuthority().equals(USER)){
            throw new IllegalArgumentException("주인이 아닙니다.");
        }

        if(!findStore.getUser().getId().equals(loginUser.getId())){
            throw new IllegalArgumentException("가게주인이 아닙니다.");
        }

        Menu findMenu = menuRepository.findById(findStore.getId()).orElseThrow(() -> new IllegalArgumentException("메뉴 를 찾을 수 없습니다."));

        findMenu.updateMenu(requestDto.getName(), requestDto.getPrice(), requestDto.getMenuDelete());
        return Menu.toDto(findMenu);
    }
    @Transactional
    public void deleteMenu(Long storeId, Long userId,MenuRequestDto requestDto) {
        Store findStore = storeService.findById(storeId);
        User loginUser = userService.findById(userId);


        if(loginUser.getAuthority().equals(USER)){
            throw new IllegalArgumentException("주인이 아닙니다.");
        }

        if(!findStore.getUser().getId().equals(loginUser.getId())){
            throw new IllegalArgumentException("가게주인이 아닙니다.");
        }

        Menu findMenu = menuRepository.findById(findStore.getId()).orElseThrow(() -> new IllegalArgumentException("메뉴 를 찾을 수 없습니다."));

        findMenu.setMenu();
        menuRepository.save(findMenu);
    }

}

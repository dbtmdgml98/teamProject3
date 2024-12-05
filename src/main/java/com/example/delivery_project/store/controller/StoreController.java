package com.example.delivery_project.store.controller;

import com.example.delivery_project.store.dto.ReadAllStoreResponseDto;
import com.example.delivery_project.store.dto.ReadStoreResponseDto;
import com.example.delivery_project.store.dto.StoreRequestDto;
import com.example.delivery_project.store.dto.StoreResponseDto;
import com.example.delivery_project.store.dto.UpdateStoreStatusResponseDto;
import com.example.delivery_project.store.service.StoreService;
import com.example.delivery_project.user.entity.Authority;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    // 가게 생성
    @PostMapping("/owners/stores")
    public ResponseEntity<StoreResponseDto> createStore(@RequestBody StoreRequestDto storeRequestDto, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");

        StoreResponseDto createdStore = storeService.createStore(storeRequestDto, userId);

        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }

    // 가게 단건 조회
    @GetMapping("/stores/{storeId}")
    public ResponseEntity<ReadStoreResponseDto> findStoreById(@PathVariable Long storeId, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Authority authority = (Authority) session.getAttribute("userAuthority");

        ReadStoreResponseDto foundStore = storeService.findStoreById(storeId, authority);

        return new ResponseEntity<>(foundStore, HttpStatus.OK);
    }

    // 가게 다건 조회
    @GetMapping("/stores")
    public ResponseEntity<Page<ReadAllStoreResponseDto>> findAllStore(@PageableDefault(page = 1)
                                                                          @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)
                                                                          Pageable pageable,
                                                                      @RequestParam(required = false) String storeName,
                                                                      HttpServletRequest request) {

        HttpSession session = request.getSession();
        Authority authority = (Authority) session.getAttribute("userAuthority");

        Page<ReadAllStoreResponseDto> foundAllStore = storeService.findAllStore(pageable, storeName, authority);
        return new ResponseEntity<>(foundAllStore, HttpStatus.OK);
    }

    // 가게 수정
    @PatchMapping("/owners/stores/{storeId}")
    public ResponseEntity<StoreResponseDto> updateStore(@PathVariable(name = "storeId") Long storeId, @RequestBody StoreRequestDto storeRequestDto) {

        StoreResponseDto updatedStore = storeService.updateStore(storeId, storeRequestDto);

        return new ResponseEntity<>(updatedStore, HttpStatus.OK);
    }


    // 가게 폐업
    @PatchMapping("/owners/stores/status/{storeId}")
    public ResponseEntity<UpdateStoreStatusResponseDto> updateStoreStatus(@PathVariable(name = "storeId") Long storeId, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        Authority authority = (Authority) session.getAttribute("userAuthority");

        UpdateStoreStatusResponseDto updateStoreStatus = storeService.updateStoreStatus(storeId, userId, authority);

        return new ResponseEntity<>(updateStoreStatus, HttpStatus.OK);
    }


}

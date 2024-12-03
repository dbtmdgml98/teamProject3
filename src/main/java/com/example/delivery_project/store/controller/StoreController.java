package com.example.delivery_project.store.controller;

import com.example.delivery_project.store.dto.StoreRequestDto;
import com.example.delivery_project.store.dto.StoreResponseDto;
import com.example.delivery_project.store.dto.UpdateStoreStatusResponseDto;
import com.example.delivery_project.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    // 가게 생성
    @PostMapping
    public ResponseEntity<StoreResponseDto> createStore(@RequestBody StoreRequestDto storeRequestDto) {

        StoreResponseDto createdStore = storeService.createStore(storeRequestDto);

        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }

    // 가게 폐업
    @PatchMapping("/{storeId}")
    public ResponseEntity<UpdateStoreStatusResponseDto> updateStoreStatus(@PathVariable(name = "storeId") Long storeId) {

        UpdateStoreStatusResponseDto updateStoreStatus = storeService.updateStoreStatus(storeId);

        return new ResponseEntity<>(updateStoreStatus, HttpStatus.OK);
    }


}

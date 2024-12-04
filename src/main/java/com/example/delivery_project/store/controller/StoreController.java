package com.example.delivery_project.store.controller;

import com.example.delivery_project.store.dto.ReadStoreResponseDto;
import com.example.delivery_project.store.dto.StoreRequestDto;
import com.example.delivery_project.store.dto.StoreResponseDto;
import com.example.delivery_project.store.dto.UpdateStoreStatusResponseDto;
import com.example.delivery_project.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    // 가게 생성
    @PostMapping("/owners/stores")
    public ResponseEntity<StoreResponseDto> createStore(@RequestBody StoreRequestDto storeRequestDto) {

        StoreResponseDto createdStore = storeService.createStore(storeRequestDto);

        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }

    // 가게 단건 조회
    @GetMapping("/stores/{storeId}")
    public ResponseEntity<ReadStoreResponseDto> findStoreById(@PathVariable(name = "storeId") Long storeId) {

        ReadStoreResponseDto foundStore = storeService.findStoreById(storeId);

        return new ResponseEntity<>(foundStore, HttpStatus.OK);
    }

    // 가게 수정
    @PatchMapping("/owners/stores/{storeId}")
    public ResponseEntity<StoreResponseDto> updateStore(@PathVariable(name = "storeId") Long storeId, @RequestBody StoreRequestDto storeRequestDto) {

        StoreResponseDto updatedStore = storeService.updateStore(storeId, storeRequestDto);

        return new ResponseEntity<>(updatedStore, HttpStatus.OK);
    }


    // 가게 폐업
    @PatchMapping("/owners/stores/status/{storeId}")
    public ResponseEntity<UpdateStoreStatusResponseDto> updateStoreStatus(@PathVariable(name = "storeId") Long storeId) {

        UpdateStoreStatusResponseDto updateStoreStatus = storeService.updateStoreStatus(storeId);

        return new ResponseEntity<>(updateStoreStatus, HttpStatus.OK);
    }


}

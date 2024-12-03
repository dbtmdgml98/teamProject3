package com.example.delivery_project.store.service;

import com.example.delivery_project.store.dto.StoreRequestDto;
import com.example.delivery_project.store.dto.StoreResponseDto;
import com.example.delivery_project.store.entity.Store;
import com.example.delivery_project.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreResponseDto createStore(StoreRequestDto storeRequestDto) {

        // 가게 이름이 이미 존재하는 경우
        Store findName = storeRepository.findByName(storeRequestDto.getName());
        if (findName != null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "가게 이름이 이미 존재합니다.");
        }

        Store store = new Store(storeRequestDto.getName(), storeRequestDto.getOpenTime(), storeRequestDto.getCloseTime(), storeRequestDto.getMinimumOrderPrice());

        Store savedStore = storeRepository.save(store);

        return new StoreResponseDto(savedStore);
    }
}

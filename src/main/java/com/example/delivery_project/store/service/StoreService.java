package com.example.delivery_project.store.service;

import com.example.delivery_project.store.dto.ReadAllStoreResponseDto;
import com.example.delivery_project.store.dto.ReadStoreResponseDto;
import com.example.delivery_project.store.dto.StoreRequestDto;
import com.example.delivery_project.store.dto.StoreResponseDto;
import com.example.delivery_project.store.dto.UpdateStoreStatusResponseDto;
import com.example.delivery_project.store.entity.Store;
import com.example.delivery_project.store.entity.StoreStatus;
import com.example.delivery_project.store.repository.StoreRepository;
import com.example.delivery_project.user.entity.Authority;
import com.example.delivery_project.user.entity.User;
import com.example.delivery_project.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    public StoreResponseDto createStore(StoreRequestDto storeRequestDto, Long userId) {

        // 가게 이름이 이미 존재하는 경우
        Store findStoreByName = storeRepository.findByName(storeRequestDto.getName());
        if (findStoreByName != null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "가게 이름이 이미 존재합니다.");
        }

        // 폐업 상태가 아닌 가게가 3개 초과일 경우
        User findUser = userRepository.findByIdOrElseThrow(userId);
        Long countOpenStore = storeRepository.countByUserAndStoreStatus(findUser, StoreStatus.OPEN);
        if (countOpenStore > 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                "사장님은 폐업 상태가 아닌 가게를 최대 3개까지만 운영할 수 있습니다.");
        }

        Store store = new Store(storeRequestDto.getName(), storeRequestDto.getOpenTime(),
            storeRequestDto.getCloseTime(), storeRequestDto.getMinimumOrderPrice(), findUser);
        Store savedStore = storeRepository.save(store);

        return StoreResponseDto.toDto(savedStore);
    }

    public Store findById(Long id) {

        return storeRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NO_CONTENT, "해당하는 가게가 존재하지 않습니다."));
    }

    public ReadStoreResponseDto findStoreById(Long storeId, Authority authority) {

        Store findStore = findById(storeId);

        // 고객이 폐업된 가게 조회하는 경우
        if (!authority.equals(Authority.OWNER) && findStore.getStoreStatus()
            .equals(StoreStatus.CLOSE)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                "고객인 일반 유저는 폐업된 가게를 조회할 수 없습니다.");
        }

        return ReadStoreResponseDto.toDto(findStore);
    }

    @Transactional
    public Page<ReadAllStoreResponseDto> findAllStore(Pageable pageable, String storeName,
        Authority authority) {

        // 고객인 경우 -> 페이지네이션에서 폐업된 가게 제외, 사장인 경우 -> 모두 조회 가능
        Page<Store> storesPages;
        Page<Store> allByName;
        if (!authority.equals(Authority.OWNER)) {

            // storeName이 없으면 페이지 전체 조회, 있으면 검색
            if (storeName == null) {
                storesPages = storeRepository.findAllByStoreStatus(pageable, StoreStatus.OPEN);
                return storesPages.map(ReadAllStoreResponseDto::toDto);
            } else {
                allByName = storeRepository.findAllByStoreStatusAndNameIsContaining(pageable,
                    StoreStatus.OPEN, storeName);
                return allByName.map(ReadAllStoreResponseDto::toDto);
            }
        } else {

            // storeName이 없으면 페이지 전체 조회, 있으면 검색
            if (storeName == null) {
                storesPages = storeRepository.findAll(pageable);
                return storesPages.map(ReadAllStoreResponseDto::toDto);
            } else {
                allByName = storeRepository.findAllByNameIsContaining(pageable, storeName);
                return allByName.map(ReadAllStoreResponseDto::toDto);
            }
        }
    }

    public StoreResponseDto updateStore(Long id, StoreRequestDto storeRequestDto) {

        Store findStore = findById(id);

        findStore.updateStore(storeRequestDto);
        Store savedStore = storeRepository.save(findStore);

        return StoreResponseDto.toDto(savedStore);
    }

    public UpdateStoreStatusResponseDto updateStoreStatus(Long storeId, Long userId,
        Authority authority) {

        Store findStore = findById(storeId);

        // 가게 사장이 아닌 경우 (다른 사장이 삭제하는 경우 & 고객이 삭제하는 경우)
        if (!authority.equals(Authority.OWNER) || !findStore.getUser().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                "해당 가게의 사장님 권한을 가진 유저만 가게를 삭제할 수 있습니다.");
        }

        // 가게가 존재하면 폐업 상태로 변환 후 DB 저장
        findStore.closeStore();
        Store savedStore = storeRepository.save(findStore);

        return new UpdateStoreStatusResponseDto(savedStore.getId(), savedStore.getName(),
            savedStore.getStoreStatus());
    }
}

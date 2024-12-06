package com.example.delivery_project.order.service;

import com.example.delivery_project.menu.entity.Menu;
import com.example.delivery_project.menu.repository.MenuRepository;
import com.example.delivery_project.order.dto.OrderRequestDto;
import com.example.delivery_project.order.dto.OrderResponseDto;
import com.example.delivery_project.order.dto.UpdateOrderRequestDto;
import com.example.delivery_project.order.entity.Order;
import com.example.delivery_project.order.entity.OrderStatus;
import com.example.delivery_project.order.exception.ErrorCode;
import com.example.delivery_project.order.exception.OrderException;
import com.example.delivery_project.order.repository.OrderMenuRepository;
import com.example.delivery_project.order.repository.OrderRepository;
import com.example.delivery_project.store.entity.Store;
import com.example.delivery_project.user.entity.User;
import com.example.delivery_project.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;
    private final OrderMenuRepository orderMenuRepository;
    public Order findOderById(Long id) {

        return orderRepository.findByIdOrElseThrow(id);
    }
    @Transactional
    public OrderResponseDto orderFinished(OrderRequestDto orderRequestDto, Long userId) {

        User findUser = userRepository.findByIdOrElseThrow(userId);
        Menu findMenu = orderMenuRepository.findByIdOrElseThrow(orderRequestDto.getMenuId());
        Store findStore = findMenu.getStore();
        LocalTime nowTime = LocalTime.now();
        LocalTime openTime = findStore.getOpenTime();
        LocalTime closeTime = findStore.getCloseTime();

        if (!openTime.isBefore(nowTime) || !closeTime.isAfter(nowTime)) {
            throw new OrderException(ErrorCode.NOT_OPEN_OR_CLOSE_STORE);
        }

        int findPrice = findMenu.getPrice();

        if (findStore.getMinimumOrderPrice() > findPrice) {
            throw new OrderException(ErrorCode.MINIMUM_PRICE_NOT_ENOUGH);
        }

        Order order = new Order(findUser,findMenu);
        order.setOrderStatus(OrderStatus.ORDER_FINISHED);

        orderRepository.save(order);

        return new OrderResponseDto(order);
    }

    public OrderResponseDto updateOrder(Long orderId, UpdateOrderRequestDto orderRequestDto) {
//        Order findOrder = findOderById(orderId);
//        Menu findMenu = orderMenuRepository.findByIdOrElseThrow(orderRequestDto.getMenuId());
//        Store findStore = findMenu.getStore();
//
//        User findUser = userRepository.findByIdOrElseThrow(findStore.getUser());
//        findOrder.updateOrderStatus(orderRequestDto);
//
//        Order savedOrder = orderRepository.save(findOrder);
        Order findOrder = findOderById(orderId);
        findOrder.updateOrderStatus(orderRequestDto);

        Order savedOrder = orderRepository.save(findOrder);
        return new OrderResponseDto(savedOrder);
    }
}

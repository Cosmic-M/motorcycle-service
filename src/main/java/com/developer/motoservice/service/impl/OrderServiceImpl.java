package com.developer.motoservice.service.impl;

import com.developer.motoservice.model.Order;
import com.developer.motoservice.repository.OrderRepository;
import com.developer.motoservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Order get(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Cannot find any Order entity by id=" + id));
    }
}

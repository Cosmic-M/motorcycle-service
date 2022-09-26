package com.developer.motoservice.service;

import com.developer.motoservice.model.Order;

import java.math.BigDecimal;

public interface OrderService {
    Order create(Order order);

    Order add(Long id, Long motoPartId);

    void update(Order order);

    void changeStatus(Long orderId, String status);

    BigDecimal getTotalAmount(Order order);
}

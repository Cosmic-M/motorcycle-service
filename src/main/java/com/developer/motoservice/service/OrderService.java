package com.developer.motoservice.service;

import com.developer.motoservice.model.MotoPart;
import com.developer.motoservice.model.Order;

public interface OrderService {
    Order create(Order order);

    Order add(Long id, MotoPart motoPart);

    void update(Order order);

    void updateStatus(Long orderId, String status);

    Order calculateTotalAmount(Order order);
}

package com.developer.motoservice.service.impl;

import com.developer.motoservice.model.MotoPart;
import com.developer.motoservice.model.Order;
import com.developer.motoservice.model.OrderStatus;
import com.developer.motoservice.repository.MotoPartRepository;
import com.developer.motoservice.repository.OrderRepository;
import com.developer.motoservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final MotoPartRepository motoPartRepository;

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order add(Long orderId, MotoPart motoPart) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("Cannot find order by id=" + orderId));
        MotoPart fromDb = motoPartRepository
                .getMotoPartByTitleAndCost(motoPart.getTitle(), motoPart.getCost());
        if (fromDb == null) {
            fromDb = motoPartRepository.save(motoPart);
        }
        List<MotoPart> motoPartList = order.getMotoParts();
        motoPartList.add(fromDb);
        order.setMotoParts(motoPartList);
        update(order);
        return order;
    }

    @Override
    public void update(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void updateStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("Cannot find order by id=" + orderId));
        order.setStatus(OrderStatus.valueOf(status));
        update(order);
    }

    @Override
    public Order calculateTotalAmount(Order order) {
        return null;
    }
}

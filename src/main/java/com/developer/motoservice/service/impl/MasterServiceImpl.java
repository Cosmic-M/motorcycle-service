package com.developer.motoservice.service.impl;

import com.developer.motoservice.model.*;
import com.developer.motoservice.repository.FavorRepository;
import com.developer.motoservice.repository.MasterRepository;
import com.developer.motoservice.repository.OrderRepository;
import com.developer.motoservice.service.MasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MasterServiceImpl implements MasterService {
    private final MasterRepository masterRepository;
    private final OrderRepository orderRepository;
    private final FavorRepository favorRepository;

    @Override
    public Master create(Master master) {
        return masterRepository.save(master);
    }

    @Override
    public void update(Master master) {
        List<Order> orders = orderRepository.getAllByMasterId(master.getId());
        master.setOrders(orders);
        masterRepository.save(master);
    }

    @Override
    public List<Order> getOrders(Long masterId) {
        return orderRepository.getAllByMasterId(masterId);
    }

    @Override
    public BigDecimal getSalary(Long masterId) {
        List<Order> orders = orderRepository
                .getAllByMasterIdAndStatus(masterId, OrderStatus.CONFIRMED_SUCCESS);
        List<Favor> favors = favorRepository.getAllByOrderInAndStatusIs(orders, PayStatus.NOT_PAID);
        BigDecimal favorsMoney = favors.stream().map(Favor::getCost).reduce(BigDecimal::add).orElseThrow(
                () -> new RuntimeException("Cannot find any relevant favor"
                + " for master with id=" + masterId
                + ": it seems to be master smoked bamboo last month"));
        favors = favors.stream().peek(favor -> favor.setStatus(PayStatus.PAID)).collect(Collectors.toList());
        favorRepository.saveAll(favors);
        orders = orders.stream().peek(order -> order.setStatus(OrderStatus.PAID)).collect(Collectors.toList());
        orderRepository.saveAll(orders);
        return favorsMoney.multiply(BigDecimal.valueOf(0.4));
    }
}

package com.developer.motoservice.service.impl;

import com.developer.motoservice.model.Favor;
import com.developer.motoservice.model.MotoPart;
import com.developer.motoservice.model.Order;
import com.developer.motoservice.model.OrderStatus;
import com.developer.motoservice.repository.MotoPartRepository;
import com.developer.motoservice.repository.OrderRepository;
import com.developer.motoservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final MotoPartRepository motoPartRepository;

    @Override
    public Order create(Order order) {
        order.setStatus(OrderStatus.RECEIVED);
        return orderRepository.save(order);
    }

    @Override
    public Order add(Long orderId, Long motoPartId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("Cannot find order by id=" + orderId));
        MotoPart motoPart = motoPartRepository.findById(motoPartId).orElseThrow(
                () -> new RuntimeException("Cannot find moto part by id=" + motoPartId));
        List<MotoPart> motoPartList = order.getMotoParts();
        motoPartList.add(motoPart);
        order.setMotoParts(motoPartList);
        if (order.getStatus().equals(OrderStatus.IN_PROCESS)) {
            int discountPercents = getDiscountPercents(order.getOwner().getId());
            BigDecimal partsDiscount = BigDecimal.valueOf(100 - discountPercents / 100);
            order.setTotalAmount(order.getTotalAmount()
                    .add(motoPart.getCost().multiply(partsDiscount)));
        }
        update(order);
        return order;
    }

    @Override
    public void update(Order order) {
        order.setTotalAmount(getTotalAmount(order));
        orderRepository.save(order);
    }

    @Override
    public void changeStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("Cannot find order by id=" + orderId));
        OrderStatus orderStatus = OrderStatus.valueOf(status);
        if (orderStatus == OrderStatus.CONFIRMED_FAIL
                || orderStatus == OrderStatus.CONFIRMED_SUCCESS) {
            order.setCompletionOrder(ZonedDateTime.now().toLocalDateTime());
        }
        order.setStatus(OrderStatus.valueOf(status));
        update(order);
    }

    private int getDiscountPercents(Long ownerId) {
        int discountPercent = Math.toIntExact(orderRepository
                .getOrderCount(ownerId, ZonedDateTime.now().toLocalDateTime().minusYears(1)));
        return Math.min(discountPercent, 20);
    }

    @Override
    public BigDecimal getTotalAmount(Order order) {
        Long ownerId = order.getOwner().getId();
        int discountPercents = getDiscountPercents(ownerId);
        BigDecimal partsDiscount = BigDecimal.valueOf(100 - discountPercents / 100);
        BigDecimal favorsDiscount = BigDecimal.valueOf(100
                - (discountPercents * 2 > 30 ? 30 : discountPercents) / 100);
        BigDecimal motoPartsTotalAmount = order.getMotoParts().stream()
                .map(MotoPart::getCost)
                .map(cost -> cost.multiply(partsDiscount))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.valueOf(0));
        BigDecimal favorsTotalAmount = order.getFavors().stream()
                .map(Favor::getCost)
                .map(cost -> cost.multiply(favorsDiscount))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.valueOf(0));
        return motoPartsTotalAmount.equals(BigDecimal.valueOf(0)) ? favorsTotalAmount
                : (favorsTotalAmount.equals(BigDecimal.valueOf(0))) ? motoPartsTotalAmount
                : motoPartsTotalAmount.multiply(favorsDiscount);
    }
}

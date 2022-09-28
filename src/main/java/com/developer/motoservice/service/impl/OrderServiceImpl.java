package com.developer.motoservice.service.impl;

import com.developer.motoservice.model.*;
import com.developer.motoservice.repository.*;
import com.developer.motoservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final MotoPartRepository motoPartRepository;
    private final MasterRepository masterRepository;
    private final OwnerRepository ownerRepository;
    private final MotorcycleRepository motorcycleRepository;
    private final FavorRepository favorRepository;

    @Override
    public Order create(Order order) {
        order.setStatus(OrderStatus.RECEIVED);
        order.setOpenOrder(ZonedDateTime.now().toLocalDateTime());
        return orderRepository.save(order);
    }

    @Override
    public Order add(Long orderId, Long motoPartId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("Cannot find order by id=" + orderId));
        MotoPart motoPart = motoPartRepository.findById(motoPartId).orElseThrow(
                () -> new RuntimeException("Cannot find moto part by id=" + motoPartId));
        List<Long> favorIdList = order.getFavors().stream()
                .map(Favor::getId).toList();
        List<Favor> favors = favorRepository.findAllById(favorIdList);
        order.setFavors(favors);
        List<MotoPart> motoPartList = order.getMotoParts();
        motoPartList.add(motoPart);
        order.setMotoParts(motoPartList);
        if (order.getStatus().equals(OrderStatus.IN_PROCESS)) {
            int discountPercents = getDiscountPercents(order.getOwner().getId());
            BigDecimal partsDiscount = BigDecimal.valueOf((100 - discountPercents) / 100);
            order.setTotalAmount(order.getTotalAmount()
                    .add(motoPart.getCost().multiply(partsDiscount)));
        }
        update(order);
        return order;
    }

    @Override
    public void update(Order order) {
        Order fromDb = orderRepository.findById(order.getId()).orElseThrow(
                () -> new RuntimeException("cannot find order by id=" + order.getId()));
        fromDb.setDescription(order.getDescription());
//        fromDb.setMaster(masterRepository.findById(order.getId()).orElseThrow(
//                () -> new RuntimeException("Can`t find master by id=" + order.getMaster().getId())));
//        fromDb.setOwner(ownerRepository.findById(order.getOwner().getId()).orElseThrow(
//                () -> new RuntimeException("Cannot find owner by id=" + order.getOwner().getId())));
//        fromDb.setMotorcycle(motorcycleRepository.findById(order.getMotorcycle().getId()).orElseThrow(
//                () -> new RuntimeException("Cannot find motorcycle by id=" + order.getMotorcycle().getId())));
        fromDb.setMaster(order.getMaster());
        fromDb.setOwner(order.getOwner());
        fromDb.setMotorcycle(order.getMotorcycle());
        orderRepository.save(order);
    }

    @Override
    public void changeStatus(Long orderId, String status) {
        Order fromDb = orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("Cannot find order by id=" + orderId));
        OrderStatus orderStatus = OrderStatus.valueOf(status);
        if (fromDb.getStatus().equals(OrderStatus.PAID)) {
            throw new RuntimeException("You cannot affect on order with status PAID");
        }
        if (orderStatus == OrderStatus.CONFIRMED_FAIL
                || orderStatus == OrderStatus.CONFIRMED_SUCCESS) {
            fromDb.setCompletionOrder(ZonedDateTime.now().toLocalDateTime());
        }
        fromDb.setStatus(orderStatus);
        update(fromDb);
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
        BigDecimal partsDiscount = BigDecimal.valueOf((100 - discountPercents) / 100.);
        BigDecimal favorsDiscount = BigDecimal
                .valueOf((100 - (Math.min(discountPercents * 2, 30))) / 100.);
        BigDecimal motoPartsTotalAmount = order.getMotoParts().stream()
                .map(MotoPart::getCost)
                .map(cost -> cost.multiply(partsDiscount))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.valueOf(0));
        List<Favor> favors = order.getFavors().stream().toList();
        BigDecimal favorsTotalAmount;
        if (favors.size() == 1 && favors.get(0).getType().equals(FavorType.DIAGNOSTIC)) {
            favorsTotalAmount = favors.get(0).getCost().multiply(favorsDiscount);
        }
        else {
            Optional<Favor> diagnosticFavor = favors.stream()
                    .filter(favor -> favor.getType().equals(FavorType.DIAGNOSTIC))
                    .findFirst();
            diagnosticFavor.ifPresent(favor -> favor.setCost(BigDecimal.valueOf(1)));
            favorsTotalAmount = favors.stream()
                    .map(Favor::getCost)
                    .map(cost -> cost.multiply(favorsDiscount))
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.valueOf(0));
        }
        return motoPartsTotalAmount.equals(BigDecimal.valueOf(0))
                ? favorsTotalAmount : (favorsTotalAmount.equals(BigDecimal.valueOf(0)))
                ? motoPartsTotalAmount : motoPartsTotalAmount.add(favorsTotalAmount);
    }
}

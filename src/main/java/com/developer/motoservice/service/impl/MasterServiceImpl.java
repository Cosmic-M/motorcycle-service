package com.developer.motoservice.service.impl;

import com.developer.motoservice.dto.response.FavorReport;
import com.developer.motoservice.model.*;
import com.developer.motoservice.repository.FavorRepository;
import com.developer.motoservice.repository.MasterRepository;
import com.developer.motoservice.repository.OrderRepository;
import com.developer.motoservice.service.MasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

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
    @Transactional
    public BigDecimal getSalary(Long masterId) {
        List<FavorReport> report = favorRepository.getAllForSalaryCalculate(masterId);
        BigDecimal favorMoney = report.stream()
                .map(FavorReport::getFavorCost)
                .reduce(BigDecimal::add)
                .orElseThrow(() -> new RuntimeException("Cannot find any relevant favor"
                                + " for master with id=" + masterId
                                + ": it seems to be master smoked bamboo last month"));
        List<Long> favorIdList = report.stream()
                .map(FavorReport::getFavorId)
                .toList();
        List<Favor> favors = favorRepository.findAllById(favorIdList).stream()
                .peek(favor -> favor.setStatus(PayStatus.PAID))
                .toList();
        favorRepository.saveAll(favors);
        return favorMoney.multiply(BigDecimal.valueOf(0.4));
    }
}

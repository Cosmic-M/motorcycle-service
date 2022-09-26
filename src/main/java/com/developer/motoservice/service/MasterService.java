package com.developer.motoservice.service;

import com.developer.motoservice.model.Master;
import com.developer.motoservice.model.Order;

import java.math.BigDecimal;
import java.util.List;

public interface MasterService {
    Master create(Master master);

    void update(Master master);

    List<Order> getOrders(Long masterId);

    BigDecimal getSalary(Long masterId);
}

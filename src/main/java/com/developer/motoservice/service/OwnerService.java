package com.developer.motoservice.service;

import com.developer.motoservice.model.Order;
import com.developer.motoservice.model.Owner;

import java.util.List;

public interface  OwnerService {
    Owner save(Owner owner);

    void update(Owner owner);

    List<Order> getOrders(Long ownerId);
}

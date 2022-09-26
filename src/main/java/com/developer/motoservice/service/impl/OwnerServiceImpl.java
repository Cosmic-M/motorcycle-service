package com.developer.motoservice.service.impl;

import com.developer.motoservice.model.Order;
import com.developer.motoservice.model.Owner;
import com.developer.motoservice.repository.OrderRepository;
import com.developer.motoservice.repository.OwnerRepository;
import com.developer.motoservice.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final OrderRepository orderRepository;

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public void update(Owner owner) {
        Owner ownerFromDb = ownerRepository.findById(owner.getId()).orElseThrow(
                () -> new RuntimeException("Cannot find owner by id=" + owner.getId()));
        ownerFromDb.setPhoneNumber(owner.getPhoneNumber());
        ownerRepository.save(ownerFromDb);
    }

    @Override
    public List<Order> getOrders(Long ownerId) {
        return orderRepository.getAllByOwnerId(ownerId);
    }
}

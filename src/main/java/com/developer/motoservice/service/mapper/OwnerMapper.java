package com.developer.motoservice.service.mapper;

import com.developer.motoservice.dto.request.OwnerRequestDto;
import com.developer.motoservice.dto.response.OwnerResponseDto;
import com.developer.motoservice.model.Motorcycle;
import com.developer.motoservice.model.Order;
import com.developer.motoservice.model.Owner;
import com.developer.motoservice.repository.MotorcycleRepository;
import com.developer.motoservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OwnerMapper {
    private final MotorcycleRepository motorcycleRepository;
    private final OrderRepository orderRepository;

    public OwnerResponseDto toDto(Owner owner) {
        OwnerResponseDto responseDto = new OwnerResponseDto();
        responseDto.setId(owner.getId());
        responseDto.setMotorcycleIdList(owner.getMotorcycles().stream()
                .map(Motorcycle::getId)
                .collect(Collectors.toList()));
        responseDto.setOrderIdList(owner.getOrders().stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        return responseDto;
    }

    public Owner toModel(OwnerRequestDto requestDto) {
        Owner owner = new Owner();
        if (requestDto.getId() != null) {
            owner.setId(requestDto.getId());
        }
        List<Motorcycle> motorcycles = requestDto.getMotorcycleIdList().stream()
                .map(id -> motorcycleRepository.findById(id).orElseThrow(
                        () -> new RuntimeException("Cannot find motorcycle by id" + id)
                )).toList();
        owner.setMotorcycles(motorcycles);
        List<Order> orders = requestDto.getOrderIdList().stream()
                        .map(id -> orderRepository.findById(id).orElseThrow(
                                () -> new RuntimeException("Cannot find order by id=" + id)
                        ))
                .toList();
        owner.setOrders(orders);
        return owner;
    }
}

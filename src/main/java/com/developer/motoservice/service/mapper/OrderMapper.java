package com.developer.motoservice.service.mapper;

import com.developer.motoservice.dto.request.OrderRequestDto;
import com.developer.motoservice.dto.response.OrderResponseDto;
import com.developer.motoservice.model.Favor;
import com.developer.motoservice.model.MotoPart;
import com.developer.motoservice.model.Order;
import com.developer.motoservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final OwnerRepository ownerRepository;
    private final MasterRepository masterRepository;
    private final MotorcycleRepository motorcycleRepository;
    private final FavorRepository motoServiceRepository;
    private final MotoPartRepository motoPartRepository;

    public OrderResponseDto toDto(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(order.getId());
        responseDto.setOwnerId(order.getOwner().getId());
        responseDto.setMasterId(order.getMaster().getId());
        responseDto.setMotorcycleId(order.getMotorcycle().getId());
        responseDto.setDescription(order.getDescription());
        responseDto.setOpenOrder(order.getOpenOrder());
        responseDto.setFavorIdList(order.getFavors().stream()
                .map(Favor::getId)
                .collect(Collectors.toList()));
        responseDto.setMotoPartIdList(order.getMotoParts().stream()
                .map(MotoPart::getId)
                .collect(Collectors.toList()));
        responseDto.setStatus(order.getStatus());
        responseDto.setTotalAmount(order.getTotalAmount());
        responseDto.setCompletionOrder(order.getCompletionOrder());
        return responseDto;
    }

    public Order toModel(OrderRequestDto requestDto) {
        Order order = new Order();
        if (requestDto.getId() != null) {
            order.setId(requestDto.getId());
        }
        order.setOwner(ownerRepository.findById(requestDto.getOwnerId()).orElseThrow(
                () -> new RuntimeException("Cannot find owner by id=" + requestDto.getOwnerId())));
        order.setMaster(masterRepository.findById(requestDto.getMasterId()).orElseThrow(
                () -> new RuntimeException("Cant find master by id=" + requestDto.getMasterId())));
        order.setMotorcycle(motorcycleRepository.findById(requestDto.getMotorcycleId())
                .orElseThrow(() -> new RuntimeException(
                        "Cannot find motorcycle by id=" + requestDto.getMotorcycleId())));
        order.setDescription(requestDto.getDescription());
        order.setOpenOrder(requestDto.getOpenOrder());
        List<Favor> favors = requestDto.getFavorIdList().stream()
                .map(id -> motoServiceRepository.findById(id).orElseThrow(
                        () -> new RuntimeException("Cannot find moto service by id=" + id)))
                .toList();
        order.setFavors(favors);
        List<MotoPart> motoParts = requestDto.getMotoPartIdList().stream().map(
                id -> motoPartRepository.findById(id).orElseThrow(
                        () -> new RuntimeException("Cannot find moto part by id=" + id))).toList();
        order.setMotoParts(motoParts);
        order.setStatus(requestDto.getStatus());
        order.setTotalAmount(requestDto.getTotalAmount());
        order.setCompletionOrder(requestDto.getCompletionOrder());
        return order;
    }
}

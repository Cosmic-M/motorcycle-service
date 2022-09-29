package com.developer.motoservice.service.mapper;

import com.developer.motoservice.dto.request.OrderCreateRequestDto;
import com.developer.motoservice.dto.request.OrderUpdateRequestDto;
import com.developer.motoservice.dto.response.OrderResponseDto;
import com.developer.motoservice.model.Favor;
import com.developer.motoservice.model.MotoPart;
import com.developer.motoservice.model.Order;
import com.developer.motoservice.repository.MasterRepository;
import com.developer.motoservice.repository.MotorcycleRepository;
import com.developer.motoservice.repository.OrderRepository;
import com.developer.motoservice.repository.OwnerRepository;
import java.util.Collections;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final OwnerRepository ownerRepository;
    private final MasterRepository masterRepository;
    private final MotorcycleRepository motorcycleRepository;
    private final OrderRepository orderRepository;

    public OrderResponseDto toDto(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(order.getId());
        responseDto.setOwnerId(order.getOwner().getId());
        responseDto.setMasterId(order.getMaster().getId());
        responseDto.setMotorcycleId(order.getMotorcycle().getId());
        responseDto.setDescription(order.getDescription());
        responseDto.setOpenOrder(order.getOpenOrder());
        responseDto.setStatus(order.getStatus());
        responseDto.setFavorIdList(order.getFavors() != null ? order.getFavors().stream()
                .map(Favor::getId).collect(Collectors.toList()) : Collections.emptyList());
        responseDto.setMotoPartIdList(order.getFavors() != null ? order.getMotoParts().stream()
                .map(MotoPart::getId).collect(Collectors.toList()) : Collections.emptyList());
        if (order.getTotalAmount() != null) {
            responseDto.setTotalAmount(order.getTotalAmount());
        }
        if (order.getCompletionOrder() != null) {
            responseDto.setCompletionOrder(order.getCompletionOrder());
        }
        return responseDto;
    }

    public Order toModel(OrderCreateRequestDto requestDto) {
        return makeGeneralFieldsMapped(requestDto);
    }

    public Order toModel(OrderUpdateRequestDto requestDto) {
        Order order = makeGeneralFieldsMapped(requestDto);
        order.setId(requestDto.getId());
        Order fromDb = orderRepository.findById(order.getId()).orElseThrow(
                () -> new RuntimeException("Cannot find order by id=" + requestDto.getId()));
        order.setStatus(fromDb.getStatus());
        return order;
    }

    private Order makeGeneralFieldsMapped(OrderCreateRequestDto requestDto) {
        Order order = new Order();
        order.setOwner(ownerRepository.findById(requestDto.getOwnerId()).orElseThrow(
                () -> new RuntimeException("Cannot find owner by id=" + requestDto.getOwnerId())));
        order.setMaster(masterRepository.findById(requestDto.getMasterId()).orElseThrow(
                () -> new RuntimeException("Cant find master by id=" + requestDto.getMasterId())));
        order.setMotorcycle(motorcycleRepository.findById(requestDto.getMotorcycleId())
                .orElseThrow(() -> new RuntimeException(
                        "Cannot find motorcycle by id=" + requestDto.getMotorcycleId())));
        order.setDescription(requestDto.getDescription());
        return order;
    }
}

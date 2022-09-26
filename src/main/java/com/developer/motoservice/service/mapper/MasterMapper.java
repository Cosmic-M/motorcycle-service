package com.developer.motoservice.service.mapper;

import com.developer.motoservice.dto.request.MasterRequestDto;
import com.developer.motoservice.dto.response.MasterResponseDto;
import com.developer.motoservice.model.Master;
import com.developer.motoservice.model.Order;
import com.developer.motoservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MasterMapper {
    private final OrderRepository orderRepository;

    public MasterResponseDto toDto(Master master) {
        MasterResponseDto masterResponseDto = new MasterResponseDto();
        masterResponseDto.setId(master.getId());
        masterResponseDto.setFirstName(master.getFirstName());
        masterResponseDto.setLastName(master.getLastName());
        masterResponseDto.setOrderIdList(master.getOrders().stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        return masterResponseDto;
    }

    public Master toModel(MasterRequestDto masterRequestDto) {
        Master master = new Master();
        master.setFirstName(masterRequestDto.getFirstName());
        master.setLastName(masterRequestDto.getLastName());
        master.setOrders(masterRequestDto.getOrderIdList().stream()
                .map(id -> orderRepository.findById(id).orElseThrow(
                        () -> new RuntimeException("Cannot find order by id=" + id)
                ))
                .collect(Collectors.toList()));
        return master;
    }
}

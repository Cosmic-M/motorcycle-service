package com.developer.motoservice.service.mapper;

import com.developer.motoservice.dto.request.MasterCreateRequestDto;
import com.developer.motoservice.dto.request.MasterUpdateRequestDto;
import com.developer.motoservice.dto.response.MasterResponseDto;
import com.developer.motoservice.model.Master;
import com.developer.motoservice.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MasterMapper {

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

    public Master toModel(MasterCreateRequestDto requestDto) {
        Master master = new Master();
        master.setFirstName(requestDto.getFirstName());
        master.setLastName(requestDto.getLastName());
        return master;
    }

    public Master toModel(MasterUpdateRequestDto requestDto) {
        Master master = new Master();
        master.setId(requestDto.getId());
        master.setFirstName(requestDto.getFirstName());
        master.setLastName(requestDto.getLastName());
        return master;
    }
}

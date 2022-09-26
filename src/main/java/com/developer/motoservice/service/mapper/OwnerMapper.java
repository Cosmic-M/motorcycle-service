package com.developer.motoservice.service.mapper;

import com.developer.motoservice.dto.request.OwnerCreateRequestDto;
import com.developer.motoservice.dto.request.OwnerUpdateRequestDto;
import com.developer.motoservice.dto.response.OwnerResponseDto;
import com.developer.motoservice.model.Motorcycle;
import com.developer.motoservice.model.Order;
import com.developer.motoservice.model.Owner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OwnerMapper {

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

    public Owner toModel(OwnerCreateRequestDto requestDto) {
        Owner owner = new Owner();
        owner.setFirstName(requestDto.getFirstName());
        owner.setLastName(requestDto.getLastName());
        owner.setPhoneNumber(requestDto.getPhoneNumber());
        return owner;
    }

    public Owner toModel(OwnerUpdateRequestDto requestDto) {
        Owner owner = new Owner();
        owner.setId(requestDto.getId());
        owner.setPhoneNumber(requestDto.getPhoneNumber());
        return owner;
    }
}

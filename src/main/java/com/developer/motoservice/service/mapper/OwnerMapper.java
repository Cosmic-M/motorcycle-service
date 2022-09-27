package com.developer.motoservice.service.mapper;

import com.developer.motoservice.dto.request.OwnerCreateRequestDto;
import com.developer.motoservice.dto.request.OwnerUpdateRequestDto;
import com.developer.motoservice.dto.response.OwnerCreateResponseDto;
import com.developer.motoservice.model.Owner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OwnerMapper {

    public OwnerCreateResponseDto toDto(Owner owner) {
        OwnerCreateResponseDto responseDto = new OwnerCreateResponseDto();
        responseDto.setId(owner.getId());
        responseDto.setFirstName(owner.getFirstName());
        responseDto.setLastName(owner.getLastName());
        responseDto.setPhoneNumber(owner.getPhoneNumber());
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

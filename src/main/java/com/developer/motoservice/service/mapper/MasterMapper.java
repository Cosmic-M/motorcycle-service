package com.developer.motoservice.service.mapper;

import com.developer.motoservice.dto.request.MasterCreateRequestDto;
import com.developer.motoservice.dto.request.MasterUpdateRequestDto;
import com.developer.motoservice.dto.response.MasterCreateResponseDto;
import com.developer.motoservice.model.Master;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MasterMapper {

    public MasterCreateResponseDto toDto(Master master) {
        MasterCreateResponseDto masterResponseDto = new MasterCreateResponseDto();
        masterResponseDto.setId(master.getId());
        masterResponseDto.setFirstName(master.getFirstName());
        masterResponseDto.setLastName(master.getLastName());
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

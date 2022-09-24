package com.developer.motoservice.service.mapper;

import com.developer.motoservice.dto.request.MotoPartRequestDto;
import com.developer.motoservice.dto.response.MotoPartResponseDto;
import com.developer.motoservice.model.MotoPart;
import org.springframework.stereotype.Component;

@Component
public class MotoPartMapper {
    public MotoPartResponseDto toDto(MotoPart motoPart) {
        MotoPartResponseDto responseDto = new MotoPartResponseDto();
        responseDto.setId(motoPart.getId());
        responseDto.setTitle(motoPart.getTitle());
        responseDto.setCost(motoPart.getCost());
        return responseDto;
    }

    public MotoPart toModel(MotoPartRequestDto requestDto) {
        MotoPart motoPart = new MotoPart();
        motoPart.setTitle(requestDto.getTitle());
        motoPart.setCost(requestDto.getCost());
        return motoPart;
    }
}

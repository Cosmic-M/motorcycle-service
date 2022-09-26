package com.developer.motoservice.service.mapper;

import com.developer.motoservice.dto.request.MotorcycleCreateRequestDto;
import com.developer.motoservice.dto.request.MotorcycleUpdateRequestDto;
import com.developer.motoservice.dto.response.MotorcycleResponseDto;
import com.developer.motoservice.model.Motorcycle;
import com.developer.motoservice.model.Owner;
import com.developer.motoservice.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MotorcycleMapper {
    private final OwnerRepository ownerRepository;

    public MotorcycleResponseDto toDto(Motorcycle motorcycle) {
        MotorcycleResponseDto responseDto = new MotorcycleResponseDto();
        responseDto.setId(motorcycle.getId());
        responseDto.setBrand(motorcycle.getBrand());
        responseDto.setModel(motorcycle.getModel());
        responseDto.setProductionYear(motorcycle.getProductionYear());
        responseDto.setLicense(motorcycle.getLicense());
        responseDto.setOwnerId(motorcycle.getOwner().getId());
        return responseDto;
    }

    public Motorcycle toModel(MotorcycleCreateRequestDto requestDto) {
        return makeGeneralFieldsMapped(requestDto);
    }

    public Motorcycle toModel(MotorcycleUpdateRequestDto requestDto) {
        Motorcycle motorcycle = makeGeneralFieldsMapped(requestDto);
        motorcycle.setId(requestDto.getId());
        return motorcycle;
    }

    private Motorcycle makeGeneralFieldsMapped(MotorcycleCreateRequestDto requestDto) {
        Motorcycle motorcycle = new Motorcycle();
        motorcycle.setBrand(requestDto.getBrand());
        motorcycle.setModel(requestDto.getModel());
        motorcycle.setProductionYear(requestDto.getProductionYear());
        motorcycle.setLicense(requestDto.getLicense());
        Owner owner = ownerRepository.findById(requestDto.getOwnerId()).orElseThrow(
                () -> new RuntimeException("Cannot find owner by id=" + requestDto.getOwnerId()));
        motorcycle.setOwner(owner);
        return motorcycle;
    }
}

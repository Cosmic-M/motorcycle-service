package com.developer.motoservice.service.mapper;

import com.developer.motoservice.dto.request.MotorcycleRequestDto;
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

    public Motorcycle toModel(MotorcycleRequestDto requestDto) {
        Motorcycle motorcycle = new Motorcycle();
        if (requestDto.getId() != null) {
            motorcycle.setId(requestDto.getId());
        }
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

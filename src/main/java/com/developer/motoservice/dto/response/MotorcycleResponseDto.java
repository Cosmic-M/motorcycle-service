package com.developer.motoservice.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MotorcycleResponseDto {
    private Long id;
    private String brand;
    private String model;
    private int productionYear;
    private String license;
    private Long ownerId;
}

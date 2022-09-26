package com.developer.motoservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MotorcycleCreateRequestDto {
    private String brand;
    private String model;
    private Integer productionYear;
    private String license;
    private Long ownerId;
}


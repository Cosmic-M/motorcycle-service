package com.developer.motoservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreateRequestDto {
    private Long ownerId;
    private Long masterId;
    private Long motorcycleId;
    private String description;
}

package com.developer.motoservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MotorcycleUpdateRequestDto extends MotorcycleCreateRequestDto {
    private Long id;
}

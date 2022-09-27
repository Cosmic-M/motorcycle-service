package com.developer.motoservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderUpdateRequestDto extends OrderCreateRequestDto {
    private Long id;
}

package com.developer.motoservice.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MotoPartResponseDto {
    private Long id;
    private String title;
    private BigDecimal cost;
}

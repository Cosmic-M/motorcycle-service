package com.developer.motoservice.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MotoPartUpdateRequestDto {
    private Long id;
    private String title;
    private BigDecimal cost;
}

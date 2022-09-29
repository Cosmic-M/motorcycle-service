package com.developer.motoservice.dto.request;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MotoPartUpdateRequestDto {
    private Long id;
    private String title;
    private BigDecimal cost;
}

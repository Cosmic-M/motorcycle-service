package com.developer.motoservice.dto.response;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MotoPartResponseDto {
    private Long id;
    private String title;
    private BigDecimal cost;
}

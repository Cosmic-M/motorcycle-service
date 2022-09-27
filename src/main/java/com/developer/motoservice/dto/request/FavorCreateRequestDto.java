package com.developer.motoservice.dto.request;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class FavorCreateRequestDto {
    private String description;
    private Long orderId;
    private Long masterId;
    private BigDecimal cost;
}

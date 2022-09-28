package com.developer.motoservice.dto.response;

import com.developer.motoservice.model.FavorType;
import com.developer.motoservice.model.PayStatus;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class FavorResponseDto {
    private Long id;
    private String description;
    private Long orderId;
    private Long masterId;
    private BigDecimal totalCost;
    private PayStatus status;
    private FavorType type;
}

package com.developer.motoservice.dto.response;

import com.developer.motoservice.model.PayStatus;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class FavorResponseDto {
    private Long id;
    private Long orderId;
    private Long masterId;
    private BigDecimal totalCost;
    private PayStatus status;
}

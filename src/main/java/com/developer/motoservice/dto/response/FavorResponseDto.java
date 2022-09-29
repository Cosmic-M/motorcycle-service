package com.developer.motoservice.dto.response;

import com.developer.motoservice.model.Category;
import com.developer.motoservice.model.PayStatus;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavorResponseDto {
    private Long id;
    private String description;
    private Long orderId;
    private Long masterId;
    private BigDecimal totalCost;
    private PayStatus status;
    private Category category;
}

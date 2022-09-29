package com.developer.motoservice.dto.request;

import com.developer.motoservice.model.Category;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavorCreateRequestDto {
    private String description;
    private Long orderId;
    private Long masterId;
    private BigDecimal cost;
    private Category category;
}

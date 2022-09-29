package com.developer.motoservice.dto.request;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MotoPartCreateRequestDto {
    private String title;
    private BigDecimal cost;
}

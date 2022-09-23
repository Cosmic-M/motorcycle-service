package com.developer.motoservice.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Order {
    private Motorcycle motorcycle;
    private String description;
    private LocalDateTime openOrderDateTime;
    private List<MotoService> motoServices;
    private List<MotoPart> motoParts;
    private Status status;
    private BigDecimal totalAmount;
    private LocalDateTime CompletionOrderDateTime;
}

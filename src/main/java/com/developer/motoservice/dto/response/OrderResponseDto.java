package com.developer.motoservice.dto.response;

import com.developer.motoservice.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private Long ownerId;
    private Long motorcycleId;
    private String description;
    private LocalDateTime openOrder;
    private List<Long> motoServiceIdList;
    private List<Long> motoPartIdList;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private LocalDateTime completionOrder;
}

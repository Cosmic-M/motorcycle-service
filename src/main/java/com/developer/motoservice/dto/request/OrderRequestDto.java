package com.developer.motoservice.dto.request;

import com.developer.motoservice.model.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    private Long id;
    private Long ownerId;
    private Long masterId;
    private Long motorcycleId;
    private String description;
    private LocalDateTime openOrder;
    private List<Long> favorIdList;
    private List<Long> motoPartIdList;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private LocalDateTime completionOrder;
}

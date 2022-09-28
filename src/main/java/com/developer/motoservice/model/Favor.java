package com.developer.motoservice.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "favors")
public class Favor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @ManyToOne
    private Master master;
    private BigDecimal cost;
    @Enumerated(EnumType.STRING)
    private PayStatus status;
    @Enumerated(EnumType.STRING)
    private FavorType type;
}

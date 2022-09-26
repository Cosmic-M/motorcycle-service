package com.developer.motoservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;
    @ManyToOne
    @JoinColumn(name = "master_id", nullable = false)
    private Master master;
    @OneToOne
    private Motorcycle motorcycle;
    private String description;
    @Column(name = "open_order")
    private LocalDateTime openOrder;
    @OneToMany
    @Column(name = "favor_id")
    private List<Favor> favors;
    @OneToMany
    @Column(name = "moto_part_id")
    private List<MotoPart> motoParts;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    @Column(name = "completion_order")
    private LocalDateTime completionOrder;
}

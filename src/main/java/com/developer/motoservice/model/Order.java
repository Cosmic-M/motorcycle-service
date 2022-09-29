package com.developer.motoservice.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
    @OneToMany(mappedBy = "order")
    private List<Favor> favors;
    @OneToMany(fetch = FetchType.EAGER)
    private List<MotoPart> motoParts;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private BigDecimal totalAmount;
    private LocalDateTime completionOrder;
}

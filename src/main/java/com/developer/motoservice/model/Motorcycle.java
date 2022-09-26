package com.developer.motoservice.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "motorcycles")
public class Motorcycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    @Column(name = "production_year")
    private int productionYear;
    private String license;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;
}

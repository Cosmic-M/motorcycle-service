package com.developer.motoservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @OneToMany(mappedBy = "owner")
    private List<Motorcycle> motorcycles;
    @OneToMany(mappedBy = "owner")
    private List<Order> orders;
}

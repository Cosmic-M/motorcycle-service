package com.developer.motoservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Motorcycle {
    private String brand;
    private String model;
    private int year;
    private String license;
    private Owner owner;
}

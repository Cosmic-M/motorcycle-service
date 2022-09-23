package com.developer.motoservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Master {
    private String firstName;
    private String lastName;
    private List<Order> orders;
}

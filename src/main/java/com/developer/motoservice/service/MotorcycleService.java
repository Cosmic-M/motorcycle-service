package com.developer.motoservice.service;

import com.developer.motoservice.model.Motorcycle;

public interface MotorcycleService {
    Motorcycle create(Motorcycle motorcycle);

    void update(Motorcycle motorcycle);
}

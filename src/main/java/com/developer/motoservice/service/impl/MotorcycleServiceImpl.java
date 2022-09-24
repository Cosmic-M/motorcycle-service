package com.developer.motoservice.service.impl;

import com.developer.motoservice.model.Motorcycle;
import com.developer.motoservice.repository.MotorcycleRepository;
import com.developer.motoservice.service.MotorcycleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MotorcycleServiceImpl implements MotorcycleService {
    private final MotorcycleRepository motorcycleRepository;

    @Override
    public Motorcycle create(Motorcycle motorcycle) {
        return motorcycleRepository.save(motorcycle);
    }

    @Override
    public void update(Motorcycle motorcycle) {
        motorcycleRepository.save(motorcycle);
    }
}

package com.developer.motoservice.service.impl;

import com.developer.motoservice.model.Master;
import com.developer.motoservice.repository.MasterRepository;
import com.developer.motoservice.service.MasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MasterServiceImpl implements MasterService {
    private final MasterRepository masterRepository;

    @Override
    public Master create(Master master) {
        return masterRepository.save(master);
    }
}

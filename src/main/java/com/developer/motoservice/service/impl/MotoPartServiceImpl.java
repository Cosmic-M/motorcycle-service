package com.developer.motoservice.service.impl;

import com.developer.motoservice.model.MotoPart;
import com.developer.motoservice.repository.MotoPartRepository;
import com.developer.motoservice.service.MotoPartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MotoPartServiceImpl implements MotoPartService {
    private final MotoPartRepository motoPartRepository;

    @Override
    public MotoPart create(MotoPart motoPart) {
        return motoPartRepository.save(motoPart);
    }

    @Override
    public void update(MotoPart motoPart) {
        motoPartRepository.save(motoPart);
    }
}

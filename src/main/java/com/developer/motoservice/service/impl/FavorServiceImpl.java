package com.developer.motoservice.service.impl;

import com.developer.motoservice.model.Favor;
import com.developer.motoservice.model.PayStatus;
import com.developer.motoservice.repository.FavorRepository;
import com.developer.motoservice.service.FavorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavorServiceImpl implements FavorService {
    private final FavorRepository favorRepository;

    @Override
    public Favor create(Favor favor) {
        return favorRepository.save(favor);
    }

    @Override
    public void update(Favor favor) {
        favorRepository.save(favor);
    }

    @Override
    public void changeStatus(Long favorId, String status) {
        Favor favor = favorRepository.findById(favorId).orElseThrow(
                () -> new RuntimeException("Cannot find favor by id=" + favorId));
        PayStatus payStatus = PayStatus.valueOf(status);
        favor.setStatus(payStatus);
        update(favor);
    }
}

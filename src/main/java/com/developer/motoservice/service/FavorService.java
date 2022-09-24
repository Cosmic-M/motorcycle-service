package com.developer.motoservice.service;

import com.developer.motoservice.model.Favor;
import com.developer.motoservice.model.PayStatus;

public interface FavorService {
    Favor create(Favor favor);

    void update(Favor favor);

    void changeStatus(Long favorId, PayStatus status);
}

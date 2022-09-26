package com.developer.motoservice.service;

import com.developer.motoservice.model.Favor;

public interface FavorService {
    Favor create(Favor favor);

    void update(Favor favor);

    void changeStatus(Long favorId, String status);
}

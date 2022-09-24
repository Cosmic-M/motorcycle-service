package com.developer.motoservice.service;

import com.developer.motoservice.model.MotoPart;

public interface MotoPartService {
    MotoPart create(MotoPart motoPart);

    void update(MotoPart motoPart);
}

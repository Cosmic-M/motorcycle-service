package com.developer.motoservice.repository;

import com.developer.motoservice.model.MotoPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface MotoPartRepository extends JpaRepository<MotoPart, Long> {
    MotoPart getMotoPartByTitleAndCost(String title, BigDecimal cost);
}

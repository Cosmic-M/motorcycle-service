package com.developer.motoservice.repository;

import com.developer.motoservice.model.MotoPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoPartRepository extends JpaRepository<MotoPart, Long> {
}

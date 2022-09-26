package com.developer.motoservice.repository;

import com.developer.motoservice.model.Favor;
import com.developer.motoservice.model.Order;
import com.developer.motoservice.model.PayStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavorRepository extends JpaRepository<Favor, Long> {
    List<Favor> getAllByOrderInAndStatusIs(List<Order> orders, PayStatus status);
}

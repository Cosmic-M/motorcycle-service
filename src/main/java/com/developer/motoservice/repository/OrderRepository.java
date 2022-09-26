package com.developer.motoservice.repository;

import com.developer.motoservice.model.Order;
import com.developer.motoservice.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getAllByOwnerId(Long ownerId);

    List<Order> getAllByMasterId(Long masterId);

    List<Order> getAllByMasterIdAndStatus(Long masterId, OrderStatus status);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.owner.id = ?1 AND o.status = 'PAID' AND o.completionOrder > ?2")
    Long getOrderCount(Long ownerId, LocalDateTime dateTime);
}

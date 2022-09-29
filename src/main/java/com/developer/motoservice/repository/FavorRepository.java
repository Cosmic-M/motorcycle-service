package com.developer.motoservice.repository;

import com.developer.motoservice.dto.response.FavorReport;
import com.developer.motoservice.model.Favor;
import com.developer.motoservice.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FavorRepository extends JpaRepository<Favor, Long> {
    @Query(value = "SELECT f.id as favorId, f.cost as favorCost "
            + "FROM favors as f "
            + "INNER JOIN orders as o "
            + "ON f.order_id = o.id "
            + "WHERE f.master_id = ?1 AND o.status = 'PAID'",
            nativeQuery = true)
    List<FavorReport> getAllForSalaryCalculate(Long masterId);

    List<Favor> getAllByOrder(Order order);
}

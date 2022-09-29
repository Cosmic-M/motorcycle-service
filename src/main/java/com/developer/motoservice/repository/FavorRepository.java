package com.developer.motoservice.repository;

import com.developer.motoservice.dto.response.FavorReport;
import com.developer.motoservice.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FavorRepository extends JpaRepository<Favor, Long> {
    @Query(value = "SELECT f.id as favorId, f.cost as favorCost "
            + "FROM favors as f "
            + "INNER JOIN orders as o "
            + "ON f.order_id = o.id "
            + "WHERE f.master_id = ?1 AND o.status = 'PAID'",
            nativeQuery = true)
    List<FavorReport> getAllForSalaryCalculate(Long masterId);
}

package com.healthSystem.HealthMonitoring.repository;

import com.healthSystem.HealthMonitoring.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
}

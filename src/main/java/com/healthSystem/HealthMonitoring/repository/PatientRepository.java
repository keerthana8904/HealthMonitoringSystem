package com.healthSystem.HealthMonitoring.repository;

import com.healthSystem.HealthMonitoring.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    //only methods not provided by jpaRepository will be written here
}

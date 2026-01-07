package com.healthSystem.HealthMonitoring.repository;

import com.healthSystem.HealthMonitoring.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}

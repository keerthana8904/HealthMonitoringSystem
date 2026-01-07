package com.healthSystem.HealthMonitoring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor // creates constructor for all pbj
@NoArgsConstructor
@Getter // create getter setter methods for all variables
@Setter
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId; // linking to patient
    private double amount;
    private String status;

}


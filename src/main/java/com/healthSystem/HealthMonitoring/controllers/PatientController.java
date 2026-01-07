package com.healthSystem.HealthMonitoring.controllers;

import com.healthSystem.HealthMonitoring.Service.PatientService;
import com.healthSystem.HealthMonitoring.models.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private static final Logger logger =
            LoggerFactory.getLogger(PatientController.class);

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // ✅ GET ALL PATIENTS
    @GetMapping
    public ResponseEntity<Page<Patient>> getAllPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        logger.info("Controller: Get all patients");
        return ResponseEntity.ok(
                patientService.getAllPatients(page, size)
        );
    }

    // ✅ CREATE PATIENT
    @PostMapping
    public ResponseEntity<Patient> createPatient(
            @RequestBody Patient patient) {

        logger.info("Controller: Create patient");
        Patient saved = patientService.createPatient(patient);

        if (saved == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    // ✅ GET PATIENT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(
            @PathVariable Long id) {

        logger.info("Controller: Get patient {}", id);
        Patient patient = patientService.getPatientById(id);

        if (patient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patient);
    }

    // ✅ UPDATE PATIENT
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(
            @PathVariable Long id,
            @RequestBody Patient patient) {

        logger.info("Controller: Update patient {}", id);
        Patient updated =
                patientService.updatePatient(id, patient);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    // ✅ DELETE PATIENT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(
            @PathVariable Long id) {

        logger.info("Controller: Delete patient {}", id);
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}

package com.healthSystem.HealthMonitoring.controllers;

import com.healthSystem.HealthMonitoring.Service.DoctorService;
import com.healthSystem.HealthMonitoring.models.Doctor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private static final Logger logger =
            LoggerFactory.getLogger(DoctorController.class);

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // GET all doctors
    @GetMapping
    public ResponseEntity<Page<Doctor>> getAllDoctors(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "3") int size) {
        try {
            logger.info("Fetching all doctors");
            Page<Doctor> doctors = doctorService.getAllDoctors(page, size);

            return ResponseEntity.ok(doctors);

        } catch (Exception e) {
            logger.error("Error fetching doctors", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET doctor by id
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        try {
            logger.info("Fetching doctor with id {}", id);
            Doctor doctor = doctorService.getDoctorById(id);

            if (doctor == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(doctor);
        } catch (Exception e) {
            logger.error("Error fetching doctor", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // CREATE doctor
    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        try {
            logger.info("Creating doctor");
            Doctor createdDoctor = doctorService.createDoctor(doctor);

            if (createdDoctor == null) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDoctor);
        } catch (Exception e) {
            logger.error("Error creating doctor", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // UPDATE doctor
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(
            @PathVariable Long id,
            @RequestBody Doctor doctor) {
        try {
            logger.info("Updating doctor with id {}", id);
            Doctor updatedDoctor = doctorService.updateDoctor(id, doctor);

            if (updatedDoctor == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedDoctor);
        } catch (Exception e) {
            logger.error("Error updating doctor", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // DELETE doctor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        try {
            logger.info("Deleting doctor with id {}", id);
            doctorService.deleteDoctor(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error deleting doctor", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

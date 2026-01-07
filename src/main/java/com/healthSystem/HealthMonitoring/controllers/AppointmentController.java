package com.healthSystem.HealthMonitoring.controllers;

import com.healthSystem.HealthMonitoring.Service.AppointmentService;
import com.healthSystem.HealthMonitoring.models.Appointment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private static final Logger logger =
            LoggerFactory.getLogger(AppointmentController.class);

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // GET all appointments
    @GetMapping
    public ResponseEntity<Page<Appointment>> getAllAppointments(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "3") int size) {
        try {
            logger.info("Fetching all appointments");
            return ResponseEntity.ok(appointmentService.getAllAppointments(page,size));
        } catch (Exception e) {
            logger.error("Error fetching appointments", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET appointment by id
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        try {
            logger.info("Fetching appointment with id {}", id);
            Appointment appointment = appointmentService.getAppointmentById(id);

            if (appointment == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(appointment);
        } catch (Exception e) {
            logger.error("Error fetching appointment", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // CREATE appointment
    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        try {
            logger.info("Creating appointment");
            Appointment created = appointmentService.createAppointment(appointment);

            if (created == null) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            logger.error("Error creating appointment", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // UPDATE appointment
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(
            @PathVariable Long id,
            @RequestBody Appointment appointment) {
        try {
            logger.info("Updating appointment with id {}", id);
            Appointment updated = appointmentService.updateAppointment(id, appointment);

            if (updated == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            logger.error("Error updating appointment", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // DELETE appointment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        try {
            logger.info("Deleting appointment with id {}", id);
            appointmentService.deleteAppointment(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error deleting appointment", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET appointments by patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatient(
            @PathVariable Long patientId) {
        try {
            logger.info("Fetching appointments for patient {}", patientId);
            return ResponseEntity.ok(
                    appointmentService.getAppointmentsByPatient(patientId));
        } catch (Exception e) {
            logger.error("Error fetching appointments by patient", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET appointments by doctor
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(
            @PathVariable Long doctorId) {
        try {
            logger.info("Fetching appointments for doctor {}", doctorId);
            return ResponseEntity.ok(
                    appointmentService.getAppointmentsByDoctor(doctorId));
        } catch (Exception e) {
            logger.error("Error fetching appointments by doctor", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

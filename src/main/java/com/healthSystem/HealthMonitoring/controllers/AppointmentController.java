package com.healthSystem.HealthMonitoring.controllers;

import com.healthSystem.HealthMonitoring.Service.AppointmentService;
import com.healthSystem.HealthMonitoring.models.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    // Constructor Injection
    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // GET all appointments
    @GetMapping
    public List<Appointment> getAllAppointments() {
        System.out.println("Controller: Fetching all appointments");
        return appointmentService.getAllAppointments();
    }

    // GET appointment by id
    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        System.out.println("Controller: Fetching appointment with id " + id);
        return appointmentService.getAppointmentById(id);
    }

    // CREATE appointment
    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        System.out.println("Controller: Creating appointment");
        return appointmentService.createAppointment(appointment);
    }

    // UPDATE appointment
    @PutMapping("/{id}")
    public Appointment updateAppointment(
            @PathVariable Long id,
            @RequestBody Appointment appointment) {

        System.out.println("Controller: Updating appointment with id " + id);
        return appointmentService.updateAppointment(id, appointment);
    }

    // DELETE appointment
    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        System.out.println("Controller: Deleting appointment with id " + id);
        appointmentService.deleteAppointment(id);
    }

    // GET appointments by patient
    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointmentsByPatient(@PathVariable Long patientId) {
        System.out.println("Controller: Fetching appointments for patient " + patientId);
        return appointmentService.getAppointmentsByPatient(patientId);
    }

    // GET appointments by doctor
    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        System.out.println("Controller: Fetching appointments for doctor " + doctorId);
        return appointmentService.getAppointmentsByDoctor(doctorId);
    }
}

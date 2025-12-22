package com.healthSystem.HealthMonitoring.controllers;

import com.healthSystem.HealthMonitoring.Service.PatientService;
import com.healthSystem.HealthMonitoring.models.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientControllers {

    private final PatientService patientService;

    // Constructor Injection (BEST PRACTICE)
    @Autowired
    public PatientControllers(PatientService patientService) {
        this.patientService = patientService;
    }

    // GET all patients
    @GetMapping
    public List<Patient> getPatients() {
        System.out.println("Controller: Fetching all patients");
        return patientService.getAllPatients();
    }

    // POST - create patient
    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        System.out.println("Controller: Creating patient");
        return patientService.createPatient(patient);
    }

    // GET patient by ID
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        System.out.println("Controller: Fetching patient with id " + id);
        return patientService.getPatientById(id);
    }

    // UPDATE patient
    @PutMapping("/{id}")
    public Patient updatePatient(
            @PathVariable Long id,
            @RequestBody Patient patient) {

        System.out.println("Controller: Updating patient with id " + id);
        return patientService.updatePatient(id, patient);
    }

    // DELETE patient
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        System.out.println("Controller: Deleting patient with id " + id);
        patientService.deletePatient(id);
    }
}

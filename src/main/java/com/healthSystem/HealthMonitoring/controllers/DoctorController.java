package com.healthSystem.HealthMonitoring.controllers;

import com.healthSystem.HealthMonitoring.Service.DoctorService;
import com.healthSystem.HealthMonitoring.models.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    // Constructor Injection (best practice)
    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // GET all doctors
    @GetMapping
    public List<Doctor> getAllDoctors() {
        System.out.println("Controller: Fetching all doctors");
        return doctorService.getAllDoctors();
    }

    // GET doctor by id
    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        System.out.println("Controller: Fetching doctor with id " + id);
        return doctorService.getDoctorById(id);
    }

    // CREATE doctor
    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        System.out.println("Controller: Creating doctor");
        return doctorService.createDoctor(doctor);
    }

    // UPDATE doctor
    @PutMapping("/{id}")
    public Doctor updateDoctor(
            @PathVariable Long id,
            @RequestBody Doctor doctor) {

        System.out.println("Controller: Updating doctor with id " + id);
        return doctorService.updateDoctor(id, doctor);
    }

    // DELETE doctor
    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        System.out.println("Controller: Deleting doctor with id " + id);
        doctorService.deleteDoctor(id);
    }
}

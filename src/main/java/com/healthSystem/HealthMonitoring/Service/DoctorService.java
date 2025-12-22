package com.healthSystem.HealthMonitoring.Service;

import com.healthSystem.HealthMonitoring.models.Doctor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {

    // Temporary in-memory list (acts like DB for now)
    private final List<Doctor> doctors = new ArrayList<>();

    // GET all doctors
    public List<Doctor> getAllDoctors() {
        try {
            System.out.println("Service: Fetching all doctors");
            return doctors;
        } catch (Exception e) {
            System.out.println("Error fetching doctors: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // GET doctor by id
    public Doctor getDoctorById(Long id) {
        try {
            System.out.println("Service: Fetching doctor with id " + id);

            for (Doctor doctor : doctors) {
                if (doctor.getId().equals(id)) {
                    return doctor;
                }
            }
            return null; // not found
        } catch (Exception e) {
            System.out.println("Error fetching doctor: " + e.getMessage());
            return null;
        }
    }

    // CREATE doctor
    public Doctor createDoctor(Doctor doctor) {
        try {
            System.out.println("Service: Creating doctor");
            doctors.add(doctor);
            return doctor;
        } catch (Exception e) {
            System.out.println("Error creating doctor: " + e.getMessage());
            return null;
        }
    }

    // UPDATE doctor
    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        try {
            System.out.println("Service: Updating doctor with id " + id);

            for (int i = 0; i < doctors.size(); i++) {
                if (doctors.get(i).getId().equals(id)) {
                    doctors.set(i, updatedDoctor);
                    return updatedDoctor;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("Error updating doctor: " + e.getMessage());
            return null;
        }
    }

    // DELETE doctor
    public void deleteDoctor(Long id) {
        try {
            System.out.println("Service: Deleting doctor with id " + id);
            doctors.removeIf(doctor -> doctor.getId().equals(id));
        } catch (Exception e) {
            System.out.println("Error deleting doctor: " + e.getMessage());
        }
    }
}

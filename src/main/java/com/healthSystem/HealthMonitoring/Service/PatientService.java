package com.healthSystem.HealthMonitoring.Service;

import com.healthSystem.HealthMonitoring.models.Patient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    // Temporary in-memory DB
    private final List<Patient> patients = new ArrayList<>();

    // GET all patients
    public List<Patient> getAllPatients() {
        try {
            System.out.println("Service: Fetching all patients");
            return patients;
        } catch (Exception e) {
            System.out.println("Error fetching patients: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // CREATE patient
    public Patient createPatient(Patient patient) {
        try {
            System.out.println("Service: Creating patient");
            patients.add(patient);
            return patient;
        } catch (Exception e) {
            System.out.println("Error creating patient: " + e.getMessage());
            return null;
        }
    }

    // GET patient by ID
    public Patient getPatientById(Long id) {
        try {
            System.out.println("Service: Fetching patient with id " + id);

            for (Patient patient : patients) {
                if (patient.getId() != null && patient.getId().equals(id)) {
                    return patient;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("Error fetching patient: " + e.getMessage());
            return null;
        }
    }

    // UPDATE patient
    public Patient updatePatient(Long id, Patient updatedPatient) {
        try {
            System.out.println("Service: Updating patient with id " + id);

            for (int i = 0; i < patients.size(); i++) {
                if (patients.get(i).getId() != null &&
                        patients.get(i).getId().equals(id)) {

                    updatedPatient.setId(id);
                    patients.set(i, updatedPatient);
                    return updatedPatient;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("Error updating patient: " + e.getMessage());
            return null;
        }
    }

    // DELETE patient
    public void deletePatient(Long id) {
        try {
            System.out.println("Service: Deleting patient with id " + id);
            patients.removeIf(
                    patient -> patient.getId() != null &&
                            patient.getId().equals(id)
            );
        } catch (Exception e) {
            System.out.println("Error deleting patient: " + e.getMessage());
        }
    }
}

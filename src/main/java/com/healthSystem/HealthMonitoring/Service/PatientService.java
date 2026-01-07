package com.healthSystem.HealthMonitoring.Service;

import com.healthSystem.HealthMonitoring.ResourceNotFoundException;
import com.healthSystem.HealthMonitoring.models.Patient;
import com.healthSystem.HealthMonitoring.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientService {

    private static final Logger logger =
            LoggerFactory.getLogger(PatientService.class);

    private final PatientRepository patientRepository;

    // Constructor Injection
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // 1️⃣ GET ALL PATIENTS (PAGINATION)
    public Page<Patient> getAllPatients(int page, int size) {

        logger.info("Fetching all patients with pagination");

        Page<Patient> patients =
                patientRepository.findAll(PageRequest.of(page, size));

        if (patients.isEmpty()) {
            logger.warn("No patients found in database");
        }

        return patients;
    }

    // 2️⃣ CREATE PATIENT
    public Patient createPatient(Patient patient) {

        if (patient == null) {
            logger.error("Patient object is null");
            throw new IllegalArgumentException("Patient data cannot be null");
        }

        if (patient.getName() == null || patient.getName().isBlank()) {
            logger.error("Patient name is empty");
            throw new IllegalArgumentException("Patient name is required");
        }

        logger.info("Creating patient with name: {}", patient.getName());

        return patientRepository.save(patient);
    }

    // 3️⃣ GET PATIENT BY ID
    public Patient getPatientById(Long id) {

        if (id == null || id <= 0) {
            logger.error("Invalid patient ID: {}", id);
            throw new IllegalArgumentException("Invalid patient ID");
        }

        logger.info("Fetching patient with id: {}", id);

        return patientRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Patient not found with id: {}", id);
                    return new ResourceNotFoundException(
                            "Patient not found with id: " + id
                    );
                });
    }

    // 4️⃣ UPDATE PATIENT
    public Patient updatePatient(Long id, Patient updatedPatient) {

        if (updatedPatient == null) {
            throw new IllegalArgumentException(
                    "Updated patient data cannot be null"
            );
        }

        logger.info("Updating patient with id: {}", id);

        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Patient not found for update with id: {}", id);
                    return new ResourceNotFoundException(
                            "Cannot update. Patient not found with id: " + id
                    );
                });

        existingPatient.setName(updatedPatient.getName());
        existingPatient.setAge(updatedPatient.getAge());
        existingPatient.setGender(updatedPatient.getGender());

        logger.info("Patient updated successfully with id: {}", id);

        return patientRepository.save(existingPatient);
    }

    // 5️⃣ DELETE PATIENT
    public void deletePatient(Long id) {

        logger.info("Deleting patient with id: {}", id);

        if (!patientRepository.existsById(id)) {
            logger.error("Cannot delete. Patient not found with id: {}", id);
            throw new ResourceNotFoundException(
                    "Cannot delete. Patient not found with id: " + id
            );
        }

        patientRepository.deleteById(id);
        logger.info("Patient deleted successfully with id: {}", id);
    }
}

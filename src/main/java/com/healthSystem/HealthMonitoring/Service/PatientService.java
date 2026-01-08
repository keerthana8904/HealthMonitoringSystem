package com.healthSystem.HealthMonitoring.Service;

import com.healthSystem.HealthMonitoring.models.Patient;
import com.healthSystem.HealthMonitoring.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<Patient> getAllPatients() {
        try {
            logger.info("Fetching all patients");
            return patientRepository.findAll();
        } catch (Exception e) {
            logger.error("Error fetching patients", e);
            return List.of();
        }
    }

    // ✅ GET ALL PATIENTS (with pagination)
    public Page<Patient> getAllInternationalisation(int page, int size) {
        try {
            logger.info("Fetching all patients BY PAGINATION");
            return patientRepository.findAll(PageRequest.of(page, size));
        } catch (Exception e) {
            logger.error("Error fetching patients", e);
            return Page.empty();
        }
    }

    // ✅ CREATE PATIENT
    public Patient createPatient(Patient patient) {
        try {
            if (patient == null) {
                return null;
            }
            logger.info("Creating patient");
            return patientRepository.save(patient);
        } catch (Exception e) {
            logger.error("Error creating patient", e);
            return null;
        }
    }

    // ✅ GET PATIENT BY ID
    public Patient getPatientById(Long id) {
        try {
            logger.info("Fetching patient with id {}", id);
            return patientRepository.findById(id).orElse(null);
        } catch (Exception e) {
            logger.error("Error fetching patient", e);
            return null;
        }
    }

    // ✅ UPDATE PATIENT
    public Patient updatePatient(Long id, Patient updatedPatient) {
        try {
            Patient existing =
                    patientRepository.findById(id).orElse(null);

            if (existing == null || updatedPatient == null) {
                return null;
            }

            existing.setName(updatedPatient.getName());
            existing.setAge(updatedPatient.getAge());
            existing.setGender(updatedPatient.getGender());

            return patientRepository.save(existing);
        } catch (Exception e) {
            logger.error("Error updating patient", e);
            return null;
        }
    }

    // ✅ DELETE PATIENT
    public void deletePatient(Long id) {
        try {
            patientRepository.deleteById(id);
            logger.info("Deleted patient with id {}", id);
        } catch (Exception e) {
            logger.error("Error deleting patient", e);
        }
    }


}

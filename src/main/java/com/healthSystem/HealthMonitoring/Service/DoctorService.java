package com.healthSystem.HealthMonitoring.Service;

import com.healthSystem.HealthMonitoring.models.Doctor;
import com.healthSystem.HealthMonitoring.repository.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Page<Doctor> getAllDoctors(int page, int size) {
        try {
            return doctorRepository.findAll(PageRequest.of(page, size));
        } catch (Exception e) {
            log.error("Error fetching doctors", e);
            return Page.empty();
        }
    }

    public Doctor getDoctorById(Long id) {
        try {
            return doctorRepository.findById(id).orElse(null);
        } catch (Exception e) {
            log.error("Error fetching doctor by id", e);
            return null;
        }
    }

    public Doctor createDoctor(Doctor doctor) {
        try {
            if (doctor == null ||
                    doctor.getName() == null ||
                    doctor.getName().isBlank()) {
                return null;
            }
            return doctorRepository.save(doctor);
        } catch (Exception e) {
            log.error("Error creating doctor", e);
            return null;
        }
    }

    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        try {
            Doctor existing = doctorRepository.findById(id).orElse(null);

            if (existing == null || updatedDoctor == null) {
                return null;
            }

            existing.setName(updatedDoctor.getName());
            existing.setSpecialisation(updatedDoctor.getSpecialisation());

            return doctorRepository.save(existing);
        } catch (Exception e) {
            log.error("Error updating doctor", e);
            return null;
        }
    }

    public void deleteDoctor(Long id) {
        try {
            doctorRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error deleting doctor", e);
        }
    }
}

package com.healthSystem.HealthMonitoring.Service;

import com.healthSystem.HealthMonitoring.models.Appointment;
import com.healthSystem.HealthMonitoring.repository.AppointmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Page<Appointment> getAllAppointments(int page, int size) {
        try {
            return appointmentRepository.findAll(PageRequest.of(page, size));
        } catch (Exception e) {
            log.error("Error fetching appointments", e);
            return Page.empty();
        }
    }

    public Appointment getAppointmentById(Long id) {
        try {
            return appointmentRepository.findById(id).orElse(null);
        } catch (Exception e) {
            log.error("Error fetching appointment by id", e);
            return null;
        }
    }

    public Appointment createAppointment(Appointment appointment) {
        try {
            if (appointment == null ||
                    appointment.getPatientId() <= 0 ||
                    appointment.getDoctorId() <= 0 ||
                    appointment.getDate() == null) {
                return null;
            }
            return appointmentRepository.save(appointment);
        } catch (Exception e) {
            log.error("Error creating appointment", e);
            return null;
        }
    }

    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        try {
            Appointment existing =
                    appointmentRepository.findById(id).orElse(null);

            if (existing == null || updatedAppointment == null) {
                return null;
            }

            existing.setPatientId(updatedAppointment.getPatientId());
            existing.setDoctorId(updatedAppointment.getDoctorId());
            existing.setDate(updatedAppointment.getDate());

            return appointmentRepository.save(existing);
        } catch (Exception e) {
            log.error("Error updating appointment", e);
            return null;
        }
    }

    public void deleteAppointment(Long id) {
        try {
            appointmentRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error deleting appointment", e);
        }
    }

    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        try {
            return appointmentRepository.findByPatientId(patientId);
        } catch (Exception e) {
            log.error("Error fetching appointments by patient", e);
            return List.of();
        }
    }

    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        try {
            return appointmentRepository.findByDoctorId(doctorId);
        } catch (Exception e) {
            log.error("Error fetching appointments by doctor", e);
            return List.of();
        }
    }
}

package com.healthSystem.HealthMonitoring.Service;

import com.healthSystem.HealthMonitoring.models.Appointment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    // Temporary in-memory DB
    private final List<Appointment> appointments = new ArrayList<>();

    // GET all appointments
    public List<Appointment> getAllAppointments() {
        try {
            System.out.println("Service: Fetching all appointments");
            return appointments;
        } catch (Exception e) {
            System.out.println("Error fetching appointments: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // GET appointment by ID
    public Appointment getAppointmentById(Long id) {
        try {
            System.out.println("Service: Fetching appointment with id " + id);

            for (Appointment appointment : appointments) {
                if (appointment.getId() != null &&
                        appointment.getId().equals(id)) {
                    return appointment;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("Error fetching appointment: " + e.getMessage());
            return null;
        }
    }

    // CREATE appointment
    public Appointment createAppointment(Appointment appointment) {
        try {
            System.out.println("Service: Creating appointment");
            appointments.add(appointment);
            return appointment;
        } catch (Exception e) {
            System.out.println("Error creating appointment: " + e.getMessage());
            return null;
        }
    }

    // UPDATE appointment
    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        try {
            System.out.println("Service: Updating appointment with id " + id);
            return null;
        } catch (Exception e) {
            System.out.println("Error updating appointment: " + e.getMessage());
            return null;
        }
    }

    // DELETE appointment
    public void deleteAppointment(Long id) {
        try {
            System.out.println("Service: Deleting appointment with id " + id);
            appointments.removeIf(
                    appointment -> appointment.getId() != null &&
                            appointment.getId().equals(id)
            );
        } catch (Exception e) {
            System.out.println("Error deleting appointment: " + e.getMessage());
        }
    }

    // GET appointments by patient
    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        try {
            System.out.println("Service: Fetching appointments for patient " + patientId);
            return null;

        } catch (Exception e) {
            System.out.println("Error fetching appointments by patient: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // GET appointments by doctor
    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        try {
            System.out.println("Service: Fetching appointments for doctor " + doctorId);

            return null;

        } catch (Exception e) {
            System.out.println("Error fetching appointments by doctor: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

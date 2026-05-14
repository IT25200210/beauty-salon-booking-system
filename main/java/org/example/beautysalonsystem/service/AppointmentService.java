package org.example.beautysalonsystem.service;

import org.example.beautysalonsystem.model.Appointment;
import org.example.beautysalonsystem.model.OnlineAppointment;
import org.example.beautysalonsystem.model.WalkInAppointment;
import org.example.beautysalonsystem.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // CREATE
    public Appointment bookAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // READ - Get all
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // READ - Get by ID
    public Optional<Appointment> getAppointmentById(int id) {
        return appointmentRepository.findById(id);
    }

    // READ - Search
    public List<Appointment> searchAppointments(String keyword) {
        return appointmentRepository.searchAppointments(keyword);
    }

    // READ - Filter by status
    public List<Appointment> getByStatus(String status) {
        return appointmentRepository.findByStatus(status);
    }

    // UPDATE
    public Appointment updateAppointment(int id, Appointment updated) {
        Appointment existing = appointmentRepository.findById(id).orElseThrow();
        existing.setCustomerName(updated.getCustomerName());
        existing.setServiceName(updated.getServiceName());
        existing.setStaffName(updated.getStaffName());
        existing.setAppointmentDate(updated.getAppointmentDate());
        existing.setAppointmentTime(updated.getAppointmentTime());
        existing.setStatus(updated.getStatus());
        existing.setNotes(updated.getNotes());
        return appointmentRepository.save(existing);
    }

    // DELETE
    public void deleteAppointment(int id) {
        appointmentRepository.deleteById(id);
    }

    // POLYMORPHISM - show details based on type
    public String getAppointmentInfo(Appointment appointment) {
        if (appointment instanceof OnlineAppointment online) {
            return online.getAppointmentDetails();
        } else if (appointment instanceof WalkInAppointment walkIn) {
            return walkIn.getAppointmentDetails();
        } else {
            return appointment.getAppointmentDetails();
        }
    }
}

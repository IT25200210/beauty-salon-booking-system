package org.example.beautysalonsystem.repository;

import org.example.beautysalonsystem.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    // Search by customer name
    @Query("SELECT a FROM Appointment a WHERE a.customerName LIKE %:keyword%"
            + " OR a.serviceName LIKE %:keyword%")
    List<Appointment> searchAppointments(@Param("keyword") String keyword);

    // Find by status
    List<Appointment> findByStatus(String status);
}
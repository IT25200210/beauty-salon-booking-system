package org.example.beutysalonsystem.repository;

import org.example.beutysalonsystem.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query("SELECT p FROM Payment p WHERE p.customerName LIKE %:keyword%"
            + " OR p.serviceName LIKE %:keyword%")
    List<Payment> searchPayments(@Param("keyword") String keyword);

    List<Payment> findByStatus(String status);
}
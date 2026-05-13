package org.example.beutysalonsystem.repository;

import org.example.beutysalonsystem.model.SalonService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface SalonServiceRepository extends JpaRepository<SalonService, Integer> {

    @Query("SELECT s FROM SalonService s WHERE s.serviceName LIKE %:keyword%"
            + " OR s.category LIKE %:keyword%")
    List<SalonService> searchServices(@Param("keyword") String keyword);

    List<SalonService> findByStatus(String status);
}

package org.example.beutysalonsystem.repository;

import org.example.beutysalonsystem.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    @Query("SELECT f FROM Feedback f WHERE f.customerName LIKE %:keyword%"
            + " OR f.serviceName LIKE %:keyword%")
    List<Feedback> searchFeedback(@Param("keyword") String keyword);

    List<Feedback> findByStatus(String status);
}

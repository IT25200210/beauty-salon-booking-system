package org.example.beutysalonsystem.repository;

import org.example.beutysalonsystem.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

    @Query("SELECT s FROM Staff s WHERE s.name LIKE %:keyword%"
            + " OR s.specialization LIKE %:keyword%")
    List<Staff> searchStaff(@Param("keyword") String keyword);

    List<Staff> findByStatus(String status);
}

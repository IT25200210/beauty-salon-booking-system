package org.example.beutysalonsystem.service;

import org.example.beutysalonsystem.model.FullTimeStaff;
import org.example.beutysalonsystem.model.PartTimeStaff;
import org.example.beutysalonsystem.model.Staff;
import org.example.beutysalonsystem.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    // CREATE
    public Staff addStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    // READ - all
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    // READ - by ID
    public Optional<Staff> getStaffById(int id) {
        return staffRepository.findById(id);
    }

    // READ - search
    public List<Staff> searchStaff(String keyword) {
        return staffRepository.searchStaff(keyword);
    }

    // UPDATE
    public Staff updateStaff(int id, Staff updated) {
        Staff existing = staffRepository.findById(id).orElseThrow();
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setPhone(updated.getPhone());
        existing.setSpecialization(updated.getSpecialization());
        existing.setWorkingHours(updated.getWorkingHours());
        existing.setStatus(updated.getStatus());
        return staffRepository.save(existing);
    }

    // DELETE
    public void deleteStaff(int id) {
        staffRepository.deleteById(id);
    }

    // POLYMORPHISM
    public String getStaffInfo(Staff staff) {
        if (staff instanceof FullTimeStaff ft) {
            return ft.getStaffProfile();
        } else if (staff instanceof PartTimeStaff pt) {
            return pt.getStaffProfile();
        } else {
            return staff.getStaffProfile();
        }
    }
}


package com.example.beautysalonbookingsystem.service;

import com.example.beautysalonbookingsystem.model.HairService;
import com.example.beautysalonbookingsystem.model.SalonService;
import com.example.beautysalonbookingsystem.model.SkinService;
import com.example.beautysalonbookingsystem.repository.SalonServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SalonServiceManager {

    @Autowired
    private SalonServiceRepository salonServiceRepository;

    // CREATE
    public SalonService addService(SalonService service) {
        return salonServiceRepository.save(service);
    }

    // READ - all
    public List<SalonService> getAllServices() {
        return salonServiceRepository.findAll();
    }

    // READ - by id
    public Optional<SalonService> getServiceById(int id) {
        return salonServiceRepository.findById(id);
    }

    // READ - search
    public List<SalonService> searchServices(String keyword) {
        return salonServiceRepository.searchServices(keyword);
    }

    // UPDATE
    public SalonService updateService(int id, SalonService updated) {
        SalonService existing = salonServiceRepository.findById(id).orElseThrow();
        existing.setServiceName(updated.getServiceName());
        existing.setDescription(updated.getDescription());
        existing.setDuration(updated.getDuration());
        existing.setPrice(updated.getPrice());
        existing.setCategory(updated.getCategory());
        existing.setStatus(updated.getStatus());
        return salonServiceRepository.save(existing);
    }

    // DELETE
    public void deleteService(int id) {
        salonServiceRepository.deleteById(id);
    }

    // POLYMORPHISM
    public String getServiceInfo(SalonService service) {
        if (service instanceof HairService hair) {
            return hair.getServiceInfo();
        } else if (service instanceof SkinService skin) {
            return skin.getServiceInfo();
        } else {
            return service.getServiceInfo();
        }
    }
}

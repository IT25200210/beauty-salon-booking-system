package org.example.beutysalonsystem.service;

import org.example.beutysalonsystem.model.HairService;
import org.example.beutysalonsystem.model.SalonService;
import org.example.beutysalonsystem.model.SkinService;
import org.example.beutysalonsystem.repository.SalonServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SalonServiceManager {

    @Autowired
    private SalonServiceRepository salonServiceRepository;

    public SalonService addService(SalonService service) {
        return salonServiceRepository.save(service);
    }

    public List<SalonService> getAllServices() {
        return salonServiceRepository.findAll();
    }

    public Optional<SalonService> getServiceById(int id) {
        return salonServiceRepository.findById(id);
    }

    public List<SalonService> searchServices(String keyword) {
        return salonServiceRepository.searchServices(keyword);
    }

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

    public void deleteService(int id) {
        salonServiceRepository.deleteById(id);
    }

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
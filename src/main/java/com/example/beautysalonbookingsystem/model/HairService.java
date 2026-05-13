package com.example.beautysalonbookingsystem.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("HAIR")
public class HairService extends SalonService {

    public HairService() {
        setCategory("HAIR");
        setStatus("ACTIVE");
    }

    @Override
    public String getServiceInfo() {
        return "[HAIR] " + getServiceName() + " | " + getDuration() + " min | Rs. " + getPrice();
    }
}

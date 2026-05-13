package com.example.beautysalonbookingsystem.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("SKIN")
public class SkinService extends SalonService {

    public SkinService() {
        setCategory("SKIN");
        setStatus("ACTIVE");
    }

    @Override
    public String getServiceInfo() {
        return "[SKIN] " + getServiceName() + " | " + getDuration() + " min | Rs. " + getPrice();
    }
}

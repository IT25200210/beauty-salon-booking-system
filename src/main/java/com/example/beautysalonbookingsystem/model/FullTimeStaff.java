package com.example.beautysalonbookingsystem.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("FULLTIME")
public class FullTimeStaff extends Staff {

    public FullTimeStaff() {
        setWorkingHours("8:00 AM - 5:00 PM");
        setStatus("ACTIVE");
    }

    @Override
    public String getStaffProfile() {
        return "[FULL-TIME] " + getName() + " | " + getSpecialization()
                + " | Hours: " + getWorkingHours();
    }
}


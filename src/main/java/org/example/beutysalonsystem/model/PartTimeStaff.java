package org.example.beutysalonsystem.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PARTTIME")
public class PartTimeStaff extends Staff {

    public PartTimeStaff() {
        setWorkingHours("Weekends only");
        setStatus("ACTIVE");
    }

    @Override
    public String getStaffProfile() {
        return "[PART-TIME] " + getName() + " | " + getSpecialization()
                + " | Hours: " + getWorkingHours();
    }
}


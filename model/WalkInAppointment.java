package org.example.beautysalonsystem.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("WALKIN")
public class WalkInAppointment extends Appointment {

    public WalkInAppointment() {
        setStatus("CONFIRMED");
    }

    // Polymorphism: different implementation from base class
    @Override
    public String getAppointmentDetails() {
        return "[WALK-IN] " + getCustomerName() + " walked in on " + getAppointmentDate();
    }
}

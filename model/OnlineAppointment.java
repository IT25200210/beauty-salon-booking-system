package org.example.beautysalonsystem.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ONLINE")
public class OnlineAppointment extends Appointment {

    public OnlineAppointment() {
        setStatus("PENDING");
    }

    // Polymorphism: different implementation from base class
    @Override
    public String getAppointmentDetails() {
        return "[ONLINE] " + getCustomerName() + " booked online on " + getAppointmentDate();
    }
}

package org.example.beutysalonsystem.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CUSTOMER")
public class RegularUser extends User {

    public RegularUser() {
        setRole("CUSTOMER");
    }

    public String getAccessLevel() {
        return "Limited access: book and view own appointments";
    }
}
package org.example.beutysalonsystem.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ADMIN")
public class AdminUser extends User {

    public AdminUser() {
        setRole("ADMIN");
    }

    public String getAdminPrivileges() {
        return "Full access: manage users, view all appointments";
    }
}
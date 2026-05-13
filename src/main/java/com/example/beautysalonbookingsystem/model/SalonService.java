package com.example.beautysalonbookingsystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "services")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "service_type", discriminatorType = DiscriminatorType.STRING)
public class SalonService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String serviceName;
    private String description;
    private int duration;    // in minutes
    private double price;
    private String category;
    private String status;   // ACTIVE or INACTIVE

    // Getters and Setters — Encapsulation
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Polymorphism — overridden in subclasses
    public String getServiceInfo() {
        return serviceName + " | " + duration + " min | Rs. " + price;
    }
}

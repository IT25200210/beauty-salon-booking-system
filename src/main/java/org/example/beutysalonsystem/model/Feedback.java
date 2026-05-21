package org.example.beutysalonsystem.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "feedback")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "feedback_type", discriminatorType = DiscriminatorType.STRING)
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String customerName;
    private String serviceName;
    private int rating;     // 1 to 5
    private String comment;
    private LocalDate feedbackDate;
    private String status;  // VISIBLE or HIDDEN

    // Getters and Setters — Encapsulation
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public LocalDate getFeedbackDate() { return feedbackDate; }
    public void setFeedbackDate(LocalDate feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Polymorphism — overridden in subclasses
    public String getFeedbackSummary() {
        return customerName + " rated " + rating + "/5 for " + serviceName;
    }
}

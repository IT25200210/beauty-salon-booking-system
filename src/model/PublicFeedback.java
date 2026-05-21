package org.example.beutysalonsystem.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PUBLIC")
public class PublicFeedback extends Feedback {

    public PublicFeedback() {
        setStatus("VISIBLE");
    }

    @Override
    public String getFeedbackSummary() {
        return "[PUBLIC] " + getCustomerName()
                + " | Rating: " + getRating() + "/5"
                + " | Service: " + getServiceName();
    }
}


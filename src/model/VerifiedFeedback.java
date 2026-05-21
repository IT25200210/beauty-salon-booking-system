package org.example.beutysalonsystem.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("VERIFIED")
public class VerifiedFeedback extends Feedback {

    public VerifiedFeedback() {
        setStatus("VISIBLE");
    }

    @Override
    public String getFeedbackSummary() {
        return "[VERIFIED] " + getCustomerName()
                + " | Rating: " + getRating() + "/5"
                + " | Service: " + getServiceName()
                + " (Verified Customer)";
    }
}


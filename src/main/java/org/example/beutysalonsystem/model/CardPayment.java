package org.example.beutysalonsystem.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CARD")
public class CardPayment extends Payment {

    public CardPayment() {
        setPaymentMethod("CARD");
        setStatus("PAID");
    }

    @Override
    public String getPaymentSummary() {
        return "[CARD] " + getCustomerName()
                + " | Rs. " + getAmount() + " | Date: " + getPaymentDate();
    }
}
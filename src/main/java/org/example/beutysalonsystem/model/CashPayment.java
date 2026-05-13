package org.example.beutysalonsystem.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CASH")
public class CashPayment extends Payment {

    public CashPayment() {
        setPaymentMethod("CASH");
        setStatus("PAID");
    }

    @Override
    public String getPaymentSummary() {
        return "[CASH] " + getCustomerName()
                + " | Rs. " + getAmount() + " | Date: " + getPaymentDate();
    }
}
package org.example.beutysalonsystem.service;

import org.example.beutysalonsystem.model.CardPayment;
import org.example.beutysalonsystem.model.CashPayment;
import org.example.beutysalonsystem.model.Payment;
import org.example.beutysalonsystem.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(int id) {
        return paymentRepository.findById(id);
    }

    public List<Payment> searchPayments(String keyword) {
        return paymentRepository.searchPayments(keyword);
    }

    public Payment updatePayment(int id, Payment updated) {
        Payment existing = paymentRepository.findById(id).orElseThrow();
        existing.setCustomerName(updated.getCustomerName());
        existing.setServiceName(updated.getServiceName());
        existing.setAmount(updated.getAmount());
        existing.setPaymentMethod(updated.getPaymentMethod());
        existing.setPaymentDate(updated.getPaymentDate());
        existing.setStatus(updated.getStatus());
        existing.setNotes(updated.getNotes());
        return paymentRepository.save(existing);
    }

    public void deletePayment(int id) {
        paymentRepository.deleteById(id);
    }

    public String getPaymentInfo(Payment payment) {
        if (payment instanceof CashPayment cash) {
            return cash.getPaymentSummary();
        } else if (payment instanceof CardPayment card) {
            return card.getPaymentSummary();
        } else {
            return payment.getPaymentSummary();
        }
    }
}
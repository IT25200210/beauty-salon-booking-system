package org.example.beutysalonsystem.service;

import org.example.beutysalonsystem.model.Feedback;
import org.example.beutysalonsystem.model.PublicFeedback;
import org.example.beutysalonsystem.model.VerifiedFeedback;
import org.example.beutysalonsystem.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // CREATE
    public Feedback addFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    // READ - all
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    // READ - by ID
    public Optional<Feedback> getFeedbackById(int id) {
        return feedbackRepository.findById(id);
    }

    // READ - search
    public List<Feedback> searchFeedback(String keyword) {
        return feedbackRepository.searchFeedback(keyword);
    }

    // UPDATE
    public Feedback updateFeedback(int id, Feedback updated) {
        Feedback existing = feedbackRepository.findById(id).orElseThrow();
        existing.setCustomerName(updated.getCustomerName());
        existing.setServiceName(updated.getServiceName());
        existing.setRating(updated.getRating());
        existing.setComment(updated.getComment());
        existing.setFeedbackDate(updated.getFeedbackDate());
        existing.setStatus(updated.getStatus());
        return feedbackRepository.save(existing);
    }

    // DELETE
    public void deleteFeedback(int id) {
        feedbackRepository.deleteById(id);
    }

    // POLYMORPHISM
    public String getFeedbackInfo(Feedback feedback) {
        if (feedback instanceof VerifiedFeedback verified) {
            return verified.getFeedbackSummary();
        } else if (feedback instanceof PublicFeedback pub) {
            return pub.getFeedbackSummary();
        } else {
            return feedback.getFeedbackSummary();
        }
    }
}

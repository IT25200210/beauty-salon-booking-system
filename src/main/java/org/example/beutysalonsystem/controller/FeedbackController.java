package org.example.beutysalonsystem.controller;

import org.example.beutysalonsystem.model.Feedback;
import org.example.beutysalonsystem.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public String listFeedback(Model model) {
        model.addAttribute("feedbackList", feedbackService.getAllFeedback());
        return "feedback-list";
    }

    @GetMapping("/search")
    public String searchFeedback(@RequestParam String keyword, Model model) {
        model.addAttribute("feedbackList", feedbackService.searchFeedback(keyword));
        model.addAttribute("keyword", keyword);
        return "feedback-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "add-feedback";
    }

    @PostMapping("/add")
    public String addFeedback(@ModelAttribute Feedback feedback) {
        feedback.setStatus("VISIBLE");
        feedbackService.addFeedback(feedback);
        return "redirect:/feedback";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("feedback",
                feedbackService.getFeedbackById(id).orElseThrow());
        return "edit-feedback";
    }

    @PostMapping("/edit/{id}")
    public String updateFeedback(@PathVariable int id,
                                 @ModelAttribute Feedback feedback) {
        feedbackService.updateFeedback(id, feedback);
        return "redirect:/feedback";
    }

    @GetMapping("/delete/{id}")
    public String deleteFeedback(@PathVariable int id) {
        feedbackService.deleteFeedback(id);
        return "redirect:/feedback";
    }

    @GetMapping("/detail/{id}")
    public String viewDetail(@PathVariable int id, Model model) {
        Feedback f = feedbackService.getFeedbackById(id).orElseThrow();
        model.addAttribute("feedback", f);
        model.addAttribute("info", feedbackService.getFeedbackInfo(f));
        return "feedback-detail";
    }
}



package org.example.beutysalonsystem.controller;

import org.example.beutysalonsystem.model.CashPayment;
import org.example.beutysalonsystem.model.Payment;
import org.example.beutysalonsystem.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public String listPayments(Model model) {
        model.addAttribute("payments", paymentService.getAllPayments());
        return "payment-list";
    }

    @GetMapping("/search")
    public String searchPayments(@RequestParam String keyword, Model model) {
        model.addAttribute("payments", paymentService.searchPayments(keyword));
        model.addAttribute("keyword", keyword);
        return "payment-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("payment", new CashPayment());
        return "add-payment";
    }

    @PostMapping("/add")
    public String addPayment(@ModelAttribute CashPayment payment) {
        paymentService.addPayment(payment);
        return "redirect:/payments";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("payment",
                paymentService.getPaymentById(id).orElseThrow());
        return "edit-payment";
    }

    @PostMapping("/edit/{id}")
    public String updatePayment(@PathVariable int id,
                                @ModelAttribute Payment payment) {
        paymentService.updatePayment(id, payment);
        return "redirect:/payments";
    }

    @GetMapping("/delete/{id}")
    public String deletePayment(@PathVariable int id) {
        paymentService.deletePayment(id);
        return "redirect:/payments";
    }

    @GetMapping("/detail/{id}")
    public String viewDetail(@PathVariable int id, Model model) {
        Payment p = paymentService.getPaymentById(id).orElseThrow();
        model.addAttribute("payment", p);
        model.addAttribute("info", paymentService.getPaymentInfo(p));
        return "payment-detail";
    }
}
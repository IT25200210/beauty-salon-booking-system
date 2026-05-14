package org.example.beautysalonsystem.controller;

import org.example.beautysalonsystem.model.Appointment;
import org.example.beautysalonsystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // LIST ALL
    @GetMapping
    public String listAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "appointment-list";
    }

    // SEARCH
    @GetMapping("/search")
    public String searchAppointments(@RequestParam String keyword, Model model) {
        model.addAttribute("appointments", appointmentService.searchAppointments(keyword));
        model.addAttribute("keyword", keyword);
        return "appointment-list";
    }

    // SHOW BOOKING FORM
    @GetMapping("/book")
    public String showBookingForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "book-appointment";
    }

    // SUBMIT BOOKING
    @PostMapping("/book")
    public String bookAppointment(@ModelAttribute Appointment appointment) {
        appointment.setStatus("PENDING");
        appointmentService.bookAppointment(appointment);
        return "redirect:/appointments";
    }

    // SHOW EDIT FORM
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("appointment",
                appointmentService.getAppointmentById(id).orElseThrow());
        return "edit-appointment";
    }

    // SUBMIT EDIT
    @PostMapping("/edit/{id}")
    public String updateAppointment(@PathVariable int id,
                                    @ModelAttribute Appointment appointment) {
        appointmentService.updateAppointment(id, appointment);
        return "redirect:/appointments";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable int id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointments";
    }

    // VIEW DETAIL
    @GetMapping("/detail/{id}")
    public String viewDetail(@PathVariable int id, Model model) {
        Appointment a = appointmentService.getAppointmentById(id).orElseThrow();
        model.addAttribute("appointment", a);
        model.addAttribute("info", appointmentService.getAppointmentInfo(a));
        return "appointment-detail";
    }
}

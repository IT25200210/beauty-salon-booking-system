package com.example.beautysalonbookingsystem.controller;

import com.example.beautysalonbookingsystem.model.SalonService;
import com.example.beautysalonbookingsystem.service.SalonServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private SalonServiceManager salonServiceManager;

    @GetMapping
    public String listServices(Model model) {
        model.addAttribute("services", salonServiceManager.getAllServices());
        return "service-list";
    }

    @GetMapping("/search")
    public String searchServices(@RequestParam String keyword, Model model) {
        model.addAttribute("services", salonServiceManager.searchServices(keyword));
        model.addAttribute("keyword", keyword);
        return "service-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("service", new SalonService());
        return "add-service";
    }

    @PostMapping("/add")
    public String addService(@ModelAttribute SalonService service) {
        service.setStatus("ACTIVE");
        salonServiceManager.addService(service);
        return "redirect:/services";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("service",
                salonServiceManager.getServiceById(id).orElseThrow());
        return "edit-service";
    }

    @PostMapping("/edit/{id}")
    public String updateService(@PathVariable int id,
                                @ModelAttribute SalonService service) {
        salonServiceManager.updateService(id, service);
        return "redirect:/services";
    }

    @GetMapping("/delete/{id}")
    public String deleteService(@PathVariable int id) {
        salonServiceManager.deleteService(id);
        return "redirect:/services";
    }

    @GetMapping("/detail/{id}")
    public String viewDetail(@PathVariable int id, Model model) {
        SalonService s = salonServiceManager.getServiceById(id).orElseThrow();
        model.addAttribute("service", s);
        model.addAttribute("info", salonServiceManager.getServiceInfo(s));
        return "service-detail";
    }
}

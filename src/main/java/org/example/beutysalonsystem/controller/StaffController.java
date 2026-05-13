package org.example.beutysalonsystem.controller;

import org.example.beutysalonsystem.model.Staff;
import org.example.beutysalonsystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping
    public String listStaff(Model model) {
        model.addAttribute("staffList", staffService.getAllStaff());
        return "staff-list";
    }

    @GetMapping("/search")
    public String searchStaff(@RequestParam String keyword, Model model) {
        model.addAttribute("staffList", staffService.searchStaff(keyword));
        model.addAttribute("keyword", keyword);
        return "staff-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("staff", new Staff());
        return "add-staff";
    }

    @PostMapping("/add")
    public String addStaff(@ModelAttribute Staff staff) {
        staff.setStatus("ACTIVE");
        staffService.addStaff(staff);
        return "redirect:/staff";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("staff", staffService.getStaffById(id).orElseThrow());
        return "edit-staff";
    }

    @PostMapping("/edit/{id}")
    public String updateStaff(@PathVariable int id, @ModelAttribute Staff staff) {
        staffService.updateStaff(id, staff);
        return "redirect:/staff";
    }

    @GetMapping("/delete/{id}")
    public String deleteStaff(@PathVariable int id) {
        staffService.deleteStaff(id);
        return "redirect:/staff";
    }

    @GetMapping("/detail/{id}")
    public String viewDetail(@PathVariable int id, Model model) {
        Staff s = staffService.getStaffById(id).orElseThrow();
        model.addAttribute("staff", s);
        model.addAttribute("info", staffService.getStaffInfo(s));
        return "staff-detail";
    }
}


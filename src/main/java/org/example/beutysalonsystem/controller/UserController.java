package org.example.beutysalonsystem.controller;

import org.example.beutysalonsystem.model.User;
import org.example.beutysalonsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // ── LIST ALL USERS ────────────────────────────
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user-list";
    }

    // ── SEARCH USERS ──────────────────────────────
    @GetMapping("/search")
    public String searchUsers(@RequestParam String keyword, Model model) {
        model.addAttribute("users", userService.searchUsers(keyword));
        model.addAttribute("keyword", keyword);
        return "user-list";
    }

    // ── REGISTER ──────────────────────────────────
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/users";
    }

    // ── LOGIN ─────────────────────────────────────
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              Model model) {
        User user = userService.getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            if (user.getRole().equals("ADMIN")) {
                return "redirect:/users";
            } else {
                return "redirect:/users";
            }
        } else {
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        }
    }

    // ── EDIT ──────────────────────────────────────
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.getUserById(id).orElseThrow());
        return "edit-user";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable int id, @ModelAttribute User user) {
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    // ── DELETE ────────────────────────────────────
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
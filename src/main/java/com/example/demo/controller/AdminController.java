package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.ContactMessage;
import com.example.demo.repository.ContactRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ContactRepository contactRepository;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    // Login page
    @GetMapping("/login")
    public String loginPage() {
        return "admin-login";
    }

    // Handle login
    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        if (username.equals(adminUsername) && password.equals(adminPassword)) {

            List<ContactMessage> messages = contactRepository.findAll();
            model.addAttribute("messages", messages);
            return "admin-dashboard";
        }

        model.addAttribute("error", "Invalid credentials");
        return "admin-login";
    }

    // Dashboard – show messages
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("messages", contactRepository.findAll());
        return "admin-dashboard";
    }

    // DELETE message
    @PostMapping("/delete/{id}")
    public String deleteMessage(@PathVariable Integer id) {
        contactRepository.deleteById(id);
        return "redirect:/admin/dashboard";
    }
}
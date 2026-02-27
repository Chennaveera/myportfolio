package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.ContactMessage;
import com.example.demo.repository.ContactRepository;

@Controller
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @PostMapping("/contact")
    public String saveMessage(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String message,
            HttpSession session,
            Model model) {

        String loggedEmail = (String) session.getAttribute("userEmail");

        // 🔒 If not logged in → redirect to login page
        if (loggedEmail == null) {
            return "redirect:/user/login";
        }

        // 🚫 If already contacted
        if (contactRepository.existsByEmail(loggedEmail)) {
            model.addAttribute("errorMessage",
                    "You already contacted Chennaveera");
            return "index";
        }

        // ✅ Save message
        ContactMessage msg = new ContactMessage();
        msg.setName(name);
        msg.setEmail(loggedEmail);
        msg.setMessage(message);

        contactRepository.save(msg);

        model.addAttribute("successMessage",
                "Message sent successfully!");

        return "index";
    }
}

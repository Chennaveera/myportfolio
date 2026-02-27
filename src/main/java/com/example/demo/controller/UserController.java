package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
	
    @GetMapping("/login")
    public String loginPage() {
        return "user-login";
    }

	/*
	 * @PostMapping("/login") public String login(@RequestParam String email,
	 * HttpSession session) {
	 * 
	 * session.setAttribute("userEmail", email); // store email return "redirect:/";
	 */
        
        @PostMapping("/login")
        public String login(@RequestParam String email,
                            HttpSession session) {

            session.setAttribute("userEmail", email);

            // 🔁 After login redirect back to homepage
            return "redirect:/";
    }
        
        @GetMapping("/logout")
        public String logout(HttpSession session) {
            session.invalidate();  // destroy session
            return "redirect:/";
        }

}

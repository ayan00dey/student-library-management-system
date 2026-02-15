/*package com.student_library.example.student_library_management_system.controler2;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController2 {

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/auth2/login"; // Redirect if not logged in
        }
        model.addAttribute("username", username);
        return "dashboard"; // Thymeleaf page
    }
}*/

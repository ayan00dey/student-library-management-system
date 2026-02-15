/*package com.student_library.example.student_library_management_system.controler2;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.student_library.example.student_library_management_system.model.AppUser;
import com.student_library.example.student_library_management_system.repository.AppUserRepository;
import com.student_library.example.student_library_management_system.requestdto.LoginDto;
import com.student_library.example.student_library_management_system.requestdto.SignupDto;
import com.student_library.example.student_library_management_system.security.JwtUtil;
import com.student_library.example.student_library_management_system.service.AuthService;

@Controller
@RequestMapping("/auth2")
public class AuthController2 {
    @Autowired
    private AuthService authService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private AppUserRepository appUserRepository;
    // Show signup page
    @GetMapping("/signup")
    public String signUp(Model model){
        model.addAttribute("signupDto",new SignupDto());
        return "signup";
    }

    // Handle signup form submission
    @PostMapping("/signupSubmit")
    public String signupSubmit(@ModelAttribute SignupDto signupDto, Model model) {
        try {
            authService.signup(signupDto);
            model.addAttribute("message", "Signup successful! Please login.");
            return "login"; // Redirect to login page
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "signup"; // Stay on signup page with error
        }
    }
    // Show login page
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginDto",new LoginDto());
        return "login";
    }

    // Handle login form submission
    @PostMapping("/loginSubmit")
    public String loginSubmit(@ModelAttribute LoginDto loginDto, Model model) {
        try {
            // Authenticate user with AuthenticationManager
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(), loginDto.getPassword()
                )
            );

            // Load the user from DB
            AppUser user = appUserRepository.findByUsername(loginDto.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Generate JWT token
            String jwtToken = jwtUtil.generateToken(user);

            // Pass username and JWT token to Thymeleaf
            model.addAttribute("username", user.getUsername());
            model.addAttribute("jwtToken", jwtToken);

            return "redirect:/dashboard"; // Thymeleaf dashboard
        } catch (AuthenticationException e) {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}*/

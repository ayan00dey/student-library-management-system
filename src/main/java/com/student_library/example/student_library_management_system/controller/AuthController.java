package com.student_library.example.student_library_management_system.controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student_library.example.student_library_management_system.model.AppUser;
import com.student_library.example.student_library_management_system.repository.AppUserRepository;
import com.student_library.example.student_library_management_system.requestdto.LoginDto;
import com.student_library.example.student_library_management_system.requestdto.SignupDto;
import com.student_library.example.student_library_management_system.security.JwtUtil;
import com.student_library.example.student_library_management_system.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AppUserRepository appUserRepository;

   @PostMapping("/signup")
public ResponseEntity<String> signup(@RequestBody SignupDto request) {
    authService.signup(request);
    return ResponseEntity.ok("Signup successful");
}


     @PostMapping("/login")
public ResponseEntity<String> login(@RequestBody LoginDto request) {
    //System.out.println("LOGIN API HIT");
    try {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
    } catch (org.springframework.security.core.AuthenticationException e) {
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    // Load the actual AppUser from the DB
    AppUser user = appUserRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));

    // Generate token with userId and username
    String token = jwtUtil.generateToken(user);

    return ResponseEntity.ok(token);
}
}

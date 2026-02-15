package com.student_library.example.student_library_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.student_library.example.student_library_management_system.model.AppUser;
import com.student_library.example.student_library_management_system.repository.AppUserRepository;
import com.student_library.example.student_library_management_system.requestdto.SignupDto;

import io.micrometer.core.ipc.http.HttpSender;

@Service
public class AuthService {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void signup(SignupDto signupDto) {
    // Check if username and password are the same (case-insensitive)
    if (signupDto.getUsername().equalsIgnoreCase(signupDto.getPassword())) {
        throw new RuntimeException("Username and password cannot be the same");
    }

    // Optional: check if username already exists
    if (appUserRepository.findByUsername(signupDto.getUsername()).isPresent()) {
        throw new RuntimeException("Username already exists");
    }

    AppUser appUser = new AppUser();
    appUser.setUsername(signupDto.getUsername());
    appUser.setPassword(passwordEncoder.encode(signupDto.getPassword()));
    appUserRepository.save(appUser);
}


    public AppUser login(String username, String password){
        AppUser appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User does not exist"));
        if(!passwordEncoder.matches(password,appUser.getPassword())){
            throw new RuntimeException("Invalid password");
        }
        return appUser;
    }
}

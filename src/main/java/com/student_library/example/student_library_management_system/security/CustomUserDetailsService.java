package com.student_library.example.student_library_management_system.security;

import com.student_library.example.student_library_management_system.model.AppUser;
import com.student_library.example.student_library_management_system.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return User.withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .authorities("USER") // You can set roles here if needed
                .build();
    }
}
// this class connect database to spring security and fetch user details when user try to login and then spring security will compare the password with the one in database and if it match then it will generate token for that user and return it to the client.
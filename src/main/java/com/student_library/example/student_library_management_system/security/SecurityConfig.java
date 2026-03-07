package com.student_library.example.student_library_management_system.security;

import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    //This class configures Spring Security for your application.
   //It defines which endpoints are protected, how authentication works, and how JWT filter is used.

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**","/css/**","/js/**").permitAll() // allow API auth and static resources
                .anyRequest().authenticated()
            )
           .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    //This defines the password hashing algorithm. You are using: BCrypt
     @Bean
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }
    @Bean
public org.springframework.security.authentication.AuthenticationManager authenticationManager(
        org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration configuration
) throws Exception {
    return configuration.getAuthenticationManager();
}
}

//@Bean is used to register an object in the Spring ApplicationContext so that it can be managed by Spring and injected wherever required. In this configuration class, we use @Bean to create and expose SecurityFilterChain, PasswordEncoder, and AuthenticationManager so that Spring Security can use them for authentication and authorization.
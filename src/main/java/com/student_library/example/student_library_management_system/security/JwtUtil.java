package com.student_library.example.student_library_management_system.security;

import com.student_library.example.student_library_management_system.model.AppUser;
import com.student_library.example.student_library_management_system.repository.AppUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtil {

    @Value("${jwt.secretKey}")
    private String key;

    private final Long expirationTime = 36000000L;

    @Autowired
    private AppUserRepository appUserRepository;

    public String generateToken(AppUser user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId", user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        String username = extractUsername(token);
        Optional<AppUser> userOpt = appUserRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            return false;
        }
        // Optionally, check token expiration
        return !getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
}

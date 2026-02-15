package com.student_library.example.student_library_management_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student_library.example.student_library_management_system.model.AppUser;

public interface AppUserRepository extends  JpaRepository<AppUser, Long>{

    Optional<AppUser> findByUsername(String username);

}

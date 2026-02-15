package com.student_library.example.student_library_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student_library.example.student_library_management_system.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

}

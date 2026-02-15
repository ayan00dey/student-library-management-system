package com.student_library.example.student_library_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.student_library.example.student_library_management_system.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    //customized queries - writing our own queries

    // 1. writing our own methods with fields or attributes with jpa support
    public Student findByEmail(String email);
    public List<Student> findByDept(String dept);

    //AND/OR operation by combining two fields
    public Student findByEmailAndDept(String email,String dept);                                                                      

    public List<Student> findByEmailOrDept(String email,String dept);

    //2. writing our own queries with @Query annotation
    @Query(nativeQuery=true,value="select * from student where dept=:dept1")
    public List<Student> getStudentByDeptQuery(String dept1);
}

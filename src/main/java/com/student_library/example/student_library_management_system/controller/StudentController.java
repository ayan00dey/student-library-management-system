package com.student_library.example.student_library_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student_library.example.student_library_management_system.model.Student;
import com.student_library.example.student_library_management_system.requestdto.StudentRequestDto;
import com.student_library.example.student_library_management_system.service.StudentService;

@RestController
@RequestMapping("/student/apis")
public class StudentController {
    @Autowired
    private StudentService studentService;

    // ResponseEntity - takes the response from APIs and bins it to the reponse entity and sends it to the client
    // ResponseEntity - it is a generic class , it takes two parameters 1. response body 2. status code
    // ResponseEntity - it is a class that represents the entire HTTP response
    // 201 - created
    // 200 - ok success
    // 404 - not found
    // 400 - bad request
    // 500 - internal server error
    // 401 - unauthorized

    @PostMapping("/save")
    public ResponseEntity<?> saveStudent(@RequestBody StudentRequestDto studentRequestDto){
       try{
           String response = studentService.addStudent(studentRequestDto);
           return ResponseEntity.ok(response);
       }catch(Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
       }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findStudentById(@PathVariable int id){
        try{
           Student student = studentService.getStudentById(id);
           return ResponseEntity.ok(student);
        }catch(Exception e){
           return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @GetMapping("/findAll")
    public List<Student> findAllStudent(){
       List<Student>studentList = studentService.getAllStudents();
       return studentList;
    }

    @GetMapping("/count")
    public String countStudents(){
        String response = studentService.countStudents();
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudentById(int id){
        String response = studentService.deleteStudentById(id);
        return response;
    }

    @PutMapping("/update/{id}")
    public String updateStudentById(@PathVariable int id, @RequestBody StudentRequestDto studentRequestDto){
        String response = studentService.updateStudentById(id, studentRequestDto);
        return response;
    }

    @GetMapping("/findByPage")
    public List<Student> findByPage(@RequestParam int pageNo, @RequestParam int pageSize){
        List<Student>studentList = studentService.getStudentsBaseOnPage(pageNo, pageSize);
        return studentList;
    }

    @GetMapping("/findByEmail")
    public Student findStudentByEmail(@RequestParam String email){
        Student student = studentService.getStudentByEmail(email);
        return student;
    }

    @GetMapping("/findByEmailOrDept")
    public List<Student> findStudentByEmailOrDept(@RequestParam String email,String dept){
        List<Student>studentList = studentService.getStudentByEmailOrDept(email, dept);
        return studentList;
    }
}

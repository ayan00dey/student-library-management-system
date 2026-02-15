package com.student_library.example.student_library_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.student_library.example.student_library_management_system.converter.StudentConverter;
import com.student_library.example.student_library_management_system.model.Card;
import com.student_library.example.student_library_management_system.model.Student;
import com.student_library.example.student_library_management_system.repository.StudentRepository;
import com.student_library.example.student_library_management_system.requestdto.StudentRequestDto;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String addStudent(StudentRequestDto studentRequestDto){
        //we do not save directly studentRequestDto into databse, we have to first convert it to student model class
        // after that we can save it into database
        
        // converts requestdto into model class
        
        Student student = StudentConverter.convertsStudentRequestDtoStudent(studentRequestDto);
        // whenever students gets created card also created
        Card card = new Card();
        card.setCardStatus("Active");
        student.setCard(card);

        card.setStudent(student);
        
        studentRepository.save(student);
        return "Student added Successfully";
    }

    public Student getStudentById(int id){
        Optional<Student>studentOptional = studentRepository.findById(id);
        if(!studentOptional.isPresent()){
            throw new RuntimeException("Student not found with id : " + id);
        }
        return studentOptional.get();
    }

    public List<Student> getAllStudents(){
        List<Student>studentList = studentRepository.findAll();
        return studentList;
    }

    public String countStudents(){
        long count = studentRepository.count();
        return "Total number of students are:" + count;
    }

    public String deleteStudentById(int id){
        studentRepository.deleteById(id);
        return "Student with id :" +id+ "deleted successfully";
    }

    public String updateStudentById(int id,StudentRequestDto studentRequestDto){
        // first find student id is present or not
        //if present then update the student
        // else no update
        Student student = getStudentById(id);
        if(student!=null){
            student.setName(studentRequestDto.getName());
            student.setEmail(studentRequestDto.getEmail());
            student.setMobile(studentRequestDto.getMobile());
            student.setDob(studentRequestDto.getDob());
            student.setGender(studentRequestDto.getGender());
            student.setDept(studentRequestDto.getDept());

            studentRepository.save(student);
            return "Student updated successfully";
        }else{
            return "Student not found";
        }
    }

    /*
     * Pagination - the number of records to be displayed on a page
     * Sorting - the order in which records are displayed
     * pagenumber - the number of pages we want to see
     * pagesize - the number of records we want to see on a page
     */
    public List<Student> getStudentsBaseOnPage(int pageNo,int pageSize){
        //pagination & sorting
        Page<Student> studentPage = studentRepository.findAll(PageRequest.of(pageNo, pageSize,Sort.by("name")));
        // we should convert the page into list
        List<Student>studentList = studentPage.getContent();
       return studentList;
    }


    public Student getStudentByEmail(String email){
        Student student = studentRepository.findByEmail(email);
        return student;
    }

    public List<Student> getStudentByEmailOrDept(String email,String dept){
        List<Student>studentList = studentRepository.findByEmailOrDept(email, dept);
        return studentList;
    }
}

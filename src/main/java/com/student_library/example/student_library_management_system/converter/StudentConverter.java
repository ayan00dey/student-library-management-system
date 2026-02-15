package com.student_library.example.student_library_management_system.converter;

import com.student_library.example.student_library_management_system.model.Student;
import com.student_library.example.student_library_management_system.requestdto.StudentRequestDto;

public class StudentConverter {

    // converters - converts the coming input request into model or entity class which represent database
    // converts the studentRequestDto into student model class

    public static Student convertsStudentRequestDtoStudent(StudentRequestDto studentRequestDto){
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setEmail(studentRequestDto.getEmail());
        student.setMobile(studentRequestDto.getMobile());
        student.setDob(studentRequestDto.getDob());
        student.setGender(studentRequestDto.getGender());
        student.setAddress(studentRequestDto.getAddress());
        student.setDept(studentRequestDto.getDept());
        return student;
    }
}

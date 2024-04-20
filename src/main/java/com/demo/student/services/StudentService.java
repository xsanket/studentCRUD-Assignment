package com.demo.student.services;

import java.util.List;

import com.demo.student.model.Student;

public interface StudentService {
    Student createStudent(Student student);

    List<Student> getAllStudents();

    boolean deleteStudent(Long id);

    Student getStudentById(Long id);

    Student updateStudent(Long id, Student student);
}

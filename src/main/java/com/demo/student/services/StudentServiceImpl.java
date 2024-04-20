package com.demo.student.services;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.demo.student.entity.StudentEntity;
import com.demo.student.model.Student;
import com.demo.student.repository.StudentRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        StudentEntity studentEntity = new StudentEntity();

        BeanUtils.copyProperties(student, studentEntity);
        studentRepository.save(studentEntity);
        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        List<StudentEntity> studentEntities
                = studentRepository.findAll();

        List<Student> students = studentEntities
                .stream()
                .map(st -> new Student(
                		st.getId(),
                		st.getFirstName(),
                		st.getLastName(),
                		st.getEmailId()))
                .collect(Collectors.toList());
        return students;
    }

    @Override
    public boolean deleteStudent(Long id) {
        StudentEntity student = studentRepository.findById(id).get();
        studentRepository.delete(student);
        return true;
    }

    @Override
    public Student getStudentById(Long id) {
        StudentEntity studentEntity
                = studentRepository.findById(id).get();
        Student student = new Student();
        BeanUtils.copyProperties(studentEntity, student);
        return student;
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        StudentEntity studentEntity
                = studentRepository.findById(id).get();
        studentEntity.setEmailId(student.getEmailId());
        studentEntity.setFirstName(student.getFirstName());
        studentEntity.setLastName(student.getLastName());

        studentRepository.save(studentEntity);
        return student;
    }
}

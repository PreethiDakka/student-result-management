package com.srms.student_result_management.service;

import com.srms.student_result_management.model.Student;
import com.srms.student_result_management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student getStudentByRollNumber(String rollNumber) {
        return studentRepository.findByRollNumber(rollNumber);
    }

    public Student getStudentByUserId(Long userId) {
        return studentRepository.findByUserId(userId);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
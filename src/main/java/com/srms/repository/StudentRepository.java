package com.srms.student_result_management.repository;

import com.srms.student_result_management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByRollNumber(String rollNumber);
    Student findByUserId(Long userId);
}
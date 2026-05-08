package com.srms.student_result_management.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(nullable = false)
    private Integer marksObtained;

    private String grade;

    private String status; // PASS or FAIL
}
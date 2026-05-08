package com.srms.student_result_management.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String rollNumber;

    @Column(nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
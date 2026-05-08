package com.srms.student_result_management.controller;

import com.srms.student_result_management.model.Student;
import com.srms.student_result_management.model.User;
import com.srms.student_result_management.service.CustomUserDetailsService;
import com.srms.student_result_management.service.StudentService;
import com.srms.student_result_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;
    @GetMapping("/")
    public String root() {
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication) {
        if (authentication.getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/admin/students";
        } else {
            return "redirect:/student/results";
        }
    }
}
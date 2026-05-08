package com.srms.student_result_management.controller;

import com.srms.student_result_management.model.Result;
import com.srms.student_result_management.model.Student;
import com.srms.student_result_management.service.ResultService;
import com.srms.student_result_management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ResultService resultService;

    @GetMapping("/results")
    public String results(Authentication authentication, Model model) {
        String username = authentication.getName();
        Student student = studentService.getStudentByUserId(
                getUserIdFromUsername(username)
        );

        if (student != null) {
            List<Result> results = resultService
                    .getResultsByStudentId(student.getId());
            model.addAttribute("student", student);
            model.addAttribute("results", results);

            int total = results.stream()
                    .mapToInt(Result::getMarksObtained).sum();
            int maxTotal = results.stream()
                    .mapToInt(r -> r.getSubject().getMaxMarks()).sum();
            double percentage = maxTotal > 0
                    ? ((double) total / maxTotal) * 100 : 0;

            model.addAttribute("total", total);
            model.addAttribute("maxTotal", maxTotal);
            model.addAttribute("percentage",
                    String.format("%.2f", percentage));
        }

        return "student/results";
    }

    private Long getUserIdFromUsername(String username) {
        return studentService.getAllStudents().stream()
                .filter(s -> s.getUser() != null &&
                        s.getUser().getUsername().equals(username))
                .map(s -> s.getUser().getId())
                .findFirst()
                .orElse(null);
    }
}
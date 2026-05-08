package com.srms.student_result_management.controller;

import com.srms.student_result_management.model.Result;
import com.srms.student_result_management.model.Student;
import com.srms.student_result_management.model.Subject;
import com.srms.student_result_management.model.User;
import com.srms.student_result_management.service.ResultService;
import com.srms.student_result_management.service.StudentService;
import com.srms.student_result_management.service.UserService;
import com.srms.student_result_management.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ResultService resultService;

    @Autowired
    private UserService userService;

    @Autowired
    private SubjectRepository subjectRepository;

    // ── Students ──────────────────────────────────────────

    @GetMapping("/students")
    public String students(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "admin/students";
    }

    @PostMapping("/students/add")
    public String addStudent(@RequestParam String name,
                             @RequestParam String rollNumber,
                             @RequestParam String email,
                             @RequestParam String username,
                             @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole("STUDENT");
        User savedUser = userService.saveUser(user);

        Student student = new Student();
        student.setName(name);
        student.setRollNumber(rollNumber);
        student.setEmail(email);
        student.setUser(savedUser);
        studentService.saveStudent(student);

        return "redirect:/admin/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/admin/students";
    }

    // ── Results ───────────────────────────────────────────

    @GetMapping("/results")
    public String results(Model model) {
        model.addAttribute("results", resultService.getAllResults());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectRepository.findAll());
        return "admin/results";
    }

    @PostMapping("/results/add")
    public String addResult(@RequestParam Long studentId,
                            @RequestParam String subjectName,
                            @RequestParam Integer maxMarks,
                            @RequestParam Integer marksObtained) {
        Subject subject = subjectRepository.findByName(subjectName);
        if (subject == null) {
            subject = new Subject();
            subject.setName(subjectName);
            subject.setMaxMarks(maxMarks);
            subject = subjectRepository.save(subject);
        }

        Result result = new Result();
        result.setStudent(studentService.getStudentById(studentId));
        result.setSubject(subject);
        result.setMarksObtained(marksObtained);
        resultService.saveResult(result);

        return "redirect:/admin/results";
    }

    @GetMapping("/results/delete/{id}")
    public String deleteResult(@PathVariable Long id) {
        resultService.deleteResult(id);
        return "redirect:/admin/results";
    }
}
package com.srms.student_result_management.service;

import com.srms.student_result_management.model.Result;
import com.srms.student_result_management.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    public Result saveResult(Result result) {
        result.setGrade(calculateGrade(result.getMarksObtained(),
                result.getSubject().getMaxMarks()));
        result.setStatus(result.getMarksObtained() >= 35 ? "PASS" : "FAIL");
        return resultRepository.save(result);
    }

    public List<Result> getResultsByStudentId(Long studentId) {
        return resultRepository.findByStudentId(studentId);
    }

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }

    private String calculateGrade(int marks, int maxMarks) {
        double percentage = ((double) marks / maxMarks) * 100;
        if (percentage >= 90) return "A+";
        else if (percentage >= 80) return "A";
        else if (percentage >= 70) return "B";
        else if (percentage >= 60) return "C";
        else if (percentage >= 35) return "D";
        else return "F";
    }
}
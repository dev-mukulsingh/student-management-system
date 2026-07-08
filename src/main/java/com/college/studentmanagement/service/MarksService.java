package com.college.studentmanagement.service;

import com.college.studentmanagement.model.Marks;
import com.college.studentmanagement.model.ReportSummary;
import com.college.studentmanagement.repository.MarksRepository;
import com.college.studentmanagement.util.GradeCalculator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarksService {

    private final MarksRepository marksRepository;

    public MarksService(MarksRepository marksRepository) {
        this.marksRepository = marksRepository;
    }

    public List<Marks> getMarksByStudentId(Long studentId) {
        return marksRepository.findByStudentId(studentId);
    }

    public Marks getMarksById(Long id) {
        return marksRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marks record not found with id: " + id));
    }

    public Marks saveMarks(Marks marks) {
        return marksRepository.save(marks);
    }

    public void deleteMarks(Long id) {
        marksRepository.deleteById(id);
    }

    /**
     * Builds the report summary (total marks, percentage, grade)
     * for a given list of marks belonging to one student.
     */
    public ReportSummary buildReport(List<Marks> marksList) {
        double totalObtained = 0;
        double totalMax = 0;

        for (Marks m : marksList) {
            totalObtained += m.getMarksObtained();
            totalMax += m.getMaxMarks();
        }

        double percentage = totalMax > 0 ? (totalObtained / totalMax) * 100 : 0;
        String grade = GradeCalculator.getGrade(percentage);

        return new ReportSummary(totalObtained, totalMax, percentage, grade);
    }
}

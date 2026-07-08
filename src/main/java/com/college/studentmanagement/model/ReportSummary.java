package com.college.studentmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Holds the calculated summary (total marks, percentage, grade)
 * for a student's report.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportSummary {
    private double totalObtained;
    private double totalMax;
    private double percentage;
    private String grade;
}

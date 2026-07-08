package com.college.studentmanagement.controller;

import com.college.studentmanagement.model.Marks;
import com.college.studentmanagement.model.ReportSummary;
import com.college.studentmanagement.model.Student;
import com.college.studentmanagement.service.MarksService;
import com.college.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final MarksService marksService;

    public StudentController(StudentService studentService, MarksService marksService) {
        this.studentService = studentService;
        this.marksService = marksService;
    }

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students/list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "students/form";
    }

    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "students/form";
        }

        studentService.saveStudent(student);
        redirectAttributes.addFlashAttribute("message", "Student saved successfully");
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        studentService.deleteStudent(id);
        redirectAttributes.addFlashAttribute("message", "Student deleted successfully");
        return "redirect:/students";
    }

    @GetMapping("/{id}")
    public String viewStudent(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        List<Marks> marksList = marksService.getMarksByStudentId(id);
        ReportSummary report = marksService.buildReport(marksList);

        model.addAttribute("student", student);
        model.addAttribute("marksList", marksList);
        model.addAttribute("report", report);
        return "students/details";
    }
}

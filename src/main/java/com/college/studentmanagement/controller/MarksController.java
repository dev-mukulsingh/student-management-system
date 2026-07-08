package com.college.studentmanagement.controller;

import com.college.studentmanagement.model.Marks;
import com.college.studentmanagement.model.Student;
import com.college.studentmanagement.service.MarksService;
import com.college.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/students/{studentId}/marks")
public class MarksController {

    private final MarksService marksService;
    private final StudentService studentService;

    public MarksController(MarksService marksService, StudentService studentService) {
        this.marksService = marksService;
        this.studentService = studentService;
    }

    @GetMapping("/new")
    public String showAddForm(@PathVariable Long studentId, Model model) {
        Student student = studentService.getStudentById(studentId);

        Marks marks = new Marks();
        marks.setStudent(student);

        model.addAttribute("marks", marks);
        model.addAttribute("student", student);
        return "students/marks-form";
    }

    @GetMapping("/edit/{marksId}")
    public String showEditForm(@PathVariable Long studentId, @PathVariable Long marksId, Model model) {
        Student student = studentService.getStudentById(studentId);
        Marks marks = marksService.getMarksById(marksId);

        model.addAttribute("marks", marks);
        model.addAttribute("student", student);
        return "students/marks-form";
    }

    @PostMapping("/save")
    public String saveMarks(@PathVariable Long studentId,
                             @Valid @ModelAttribute("marks") Marks marks,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttributes) {

        Student student = studentService.getStudentById(studentId);

        if (result.hasErrors()) {
            model.addAttribute("student", student);
            return "students/marks-form";
        }

        marks.setStudent(student);
        marksService.saveMarks(marks);

        redirectAttributes.addFlashAttribute("message", "Marks saved successfully");
        return "redirect:/students/" + studentId;
    }

    @GetMapping("/delete/{marksId}")
    public String deleteMarks(@PathVariable Long studentId, @PathVariable Long marksId,
                               RedirectAttributes redirectAttributes) {
        marksService.deleteMarks(marksId);
        redirectAttributes.addFlashAttribute("message", "Marks record deleted successfully");
        return "redirect:/students/" + studentId;
    }
}

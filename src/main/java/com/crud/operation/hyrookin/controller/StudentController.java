package com.crud.operation.hyrookin.controller;

import com.crud.operation.hyrookin.entity.StudentEntity;
import com.crud.operation.hyrookin.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String getAllStudents(Model model) {
        List<StudentEntity> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/add")
    public String showAddStudentForm(Model model) {
        model.addAttribute("studentEntity", new StudentEntity());
        return "add-student";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute("studentEntity") StudentEntity studentEntity) {
        studentService.addStudent(studentEntity);
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String showEditStudentForm(@PathVariable Long id, Model model) {
        StudentEntity studentEntity = studentService.getStudentById(id);
        model.addAttribute("studentEntity", studentEntity);
        return "edit-student";
    }

    @PostMapping("/{id}/edit")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("studentEntity") StudentEntity studentEntity) {
        studentService.updateStudent(id, studentEntity);
        return "redirect:/students";
    }

    @GetMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}

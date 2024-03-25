package com.crud.operation.hyrookin.controller;

import com.crud.operation.hyrookin.entity.ClassTeacherEntity;
import com.crud.operation.hyrookin.service.ClassTeacherService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachers")
public class ClassTeacherController {

    @Autowired
    private ClassTeacherService classTeacherService;

    // Display all class teachers
    @GetMapping
    public String listTeachers(Model model) {
        model.addAttribute("teachers", classTeacherService.getAllClassTeachers());
        return "teachers-list"; // Needs a template named teachers-list.html
    }

    // Show form for adding a new class teacher
    @GetMapping("/add")
    public String showAddTeacherForm(Model model) {
        model.addAttribute("classTeacherEntity", new ClassTeacherEntity());
        return "add-class-teacher"; // Needs a template named add-class-teacher.html
    }

    // Process the form for adding a new class teacher
    public String addClassTeacher(ClassTeacherEntity classTeacher, HttpSession session) {
    Long userId = (Long) session.getAttribute("userId"); // Assuming you store the user's ID in the session
    classTeacher.setCreatedBy(userId);
    classTeacher.setUpdatedBy(userId);
    // Save the entity as you normally would
    return "redirect:/teachers"; // Redirect after saving
}

    // Show form for editing an existing class teacher
    @GetMapping("/{id}/edit")
    public String showEditTeacherForm(@PathVariable Long id, Model model) {
        ClassTeacherEntity classTeacherEntity = classTeacherService.getClassTeacherById(id);
        model.addAttribute("classTeacherEntity", classTeacherEntity);
        return "edit-class-teacher"; // Needs a template named edit-class-teacher.html
    }

    // Process the form for editing an existing class teacher
    @PostMapping("/{id}/edit")
    public String updateTeacher(@PathVariable Long id, @ModelAttribute("classTeacherEntity") ClassTeacherEntity classTeacherEntity) {
        classTeacherService.updateClassTeacher(id, classTeacherEntity);
        return "redirect:/teachers";
    }

    // Delete an existing class teacher
    @GetMapping("/{id}/delete")
    public String deleteTeacher(@PathVariable Long id) {
        classTeacherService.deleteClassTeacher(id);
        return "redirect:/teachers";
    }
}

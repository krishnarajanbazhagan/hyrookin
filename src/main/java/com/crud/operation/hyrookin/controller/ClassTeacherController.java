package com.crud.operation.hyrookin.controller;

import com.crud.operation.hyrookin.entity.ClassEntity;
import com.crud.operation.hyrookin.entity.ClassTeacherEntity;
import com.crud.operation.hyrookin.service.ClassService;
import com.crud.operation.hyrookin.service.ClassTeacherService;

import java.util.List;

import javax.validation.Valid;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachers")
public class ClassTeacherController {

    @Autowired
    private ClassTeacherService classTeacherService;
    @Autowired
    private ClassService classService;

    // Display all class teachers
    @GetMapping
    public String listTeachers(Model model) {
        List<ClassTeacherEntity> teachers = classTeacherService.getAllClassTeachers();
        // Ensure ClassEntity associated with each teacher is fetched or accessible
        model.addAttribute("teachers", teachers);
        return "teachers-list"; // Adjusted template name if different
    }
    

    // Show form for adding a new class teacher
     @GetMapping("/add")
    public String showAddTeacherForm(Model model) {
        model.addAttribute("classTeacherEntity", new ClassTeacherEntity());
        model.addAttribute("classes", classService.getAllClasses()); // Fetch and add list of classes to the model
        return "add-class-teacher";
    }

    // Process the form for adding a new class teacher
    @PostMapping("/add")
public String addClassTeacher(@Valid @ModelAttribute("classTeacherEntity") ClassTeacherEntity classTeacher,
                              BindingResult result, @RequestParam("selectedClassId") Long selectedClassId, HttpSession session) {
    if (result.hasErrors()) {
        return "add-class-teacher";
    }
    
    ClassEntity classEntity = classService.getClassById(selectedClassId); // Fetch the class using the selectedClassId
    classTeacher.setClassEntity(classEntity); // Associate the fetched ClassEntity with the ClassTeacherEntity
    
    Long userId = (Long) session.getAttribute("userId");
    classTeacher.setCreatedBy(userId);
    classTeacher.setUpdatedBy(userId);
    classTeacherService.addClassTeacher(classTeacher);
    return "redirect:/teachers";
}



    // Show form for editing an existing class teacher
    @GetMapping("/{id}/edit")
    public String showEditTeacherForm(@PathVariable Long id, Model model) {
    ClassTeacherEntity classTeacherEntity = classTeacherService.getClassTeacherById(id);
    model.addAttribute("classTeacherEntity", classTeacherEntity);
    model.addAttribute("classes", classService.getAllClasses()); // Also provide list of classes for editing
    return "edit-class-teacher";
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

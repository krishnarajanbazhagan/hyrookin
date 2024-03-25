package com.crud.operation.hyrookin.controller;

import com.crud.operation.hyrookin.entity.ClassEntity;
import com.crud.operation.hyrookin.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping
    public String getAllClasses(Model model) {
        List<ClassEntity> classes = classService.getAllClasses();
        model.addAttribute("classes", classes);
        return "classes";
    }

    @GetMapping("/add")
    public String showAddClassForm(Model model) {
        model.addAttribute("classEntity", new ClassEntity());
        return "add-class";
    }

    @PostMapping("/add")
    public String addClass(@ModelAttribute("classEntity") ClassEntity classEntity) {
        classService.addClass(classEntity);
        return "redirect:/classes";
    }

    @GetMapping("/{id}/edit")
    public String showEditClassForm(@PathVariable Long id, Model model) {
        ClassEntity classEntity = classService.getClassById(id);
        model.addAttribute("classEntity", classEntity);
        return "edit-class";
    }

    @PostMapping("/{id}/edit")
    public String updateClass(@PathVariable Long id, @ModelAttribute("classEntity") ClassEntity classEntity) {
        classService.updateClass(id, classEntity);
        return "redirect:/classes";
    }

    @GetMapping("/{id}/delete")
    public String deleteClass(@PathVariable Long id) {
        classService.deleteClass(id);
        return "redirect:/classes";
    }
}

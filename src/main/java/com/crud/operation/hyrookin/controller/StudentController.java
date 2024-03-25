package com.crud.operation.hyrookin.controller;

import com.crud.operation.hyrookin.entity.ClassEntity;
import com.crud.operation.hyrookin.entity.ClassTeacherEntity;
import com.crud.operation.hyrookin.entity.StudentEntity;
import com.crud.operation.hyrookin.service.ClassService;
import com.crud.operation.hyrookin.service.ClassTeacherService;
import com.crud.operation.hyrookin.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;

    @Autowired
    private ClassTeacherService classTeacherService;

    @GetMapping
    public String getAllStudents(Model model) {
        List<StudentEntity> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/add")
    public String showAddStudentForm(Model model) {
    model.addAttribute("studentEntity", new StudentEntity());
    List<ClassEntity> classes = classService.getAllClasses(); // Sample method to fetch classes
    List<ClassTeacherEntity> teachers = classTeacherService.getAllClassTeachers(); // Sample method to fetch teachers
    model.addAttribute("classes", classes);
    model.addAttribute("teachers", teachers);
    System.out.println("Classes Size: " + classes.size()); // Debugging line
    System.out.println("Teachers Size: " + teachers.size()); // Debugging line
    return "add-student";
}
@PostMapping("/add")
public String addStudent(@ModelAttribute("studentEntity") StudentEntity studentEntity,
                         @RequestParam Optional<String> dob, // Change "dob" to Optional<String>
                         @RequestParam Optional<Long> class_id,
                         @RequestParam Optional<Long> class_teacher_id, 
                         Model model, RedirectAttributes redirectAttributes) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Adjusted to the format used by HTML date inputs

    dob.ifPresent(dateString -> {
        try {
            Date date = formatter.parse(dateString);
            studentEntity.setDateOfBirth(date);
        } catch (ParseException e) {
            e.printStackTrace();
            // Preparing the model for returning to the form with an error message
            prepareModelForForm(model);
            model.addAttribute("errorMessage", "Invalid date format. Please use yyyy-MM-dd.");
            // Return "add-student" to re-render the form with the error message
        }
    });

    class_id.ifPresent(id -> {
        ClassEntity classEntity = classService.getClassById(id);
        studentEntity.setClassEntity(classEntity);
    });

    class_teacher_id.ifPresent(id -> {
        ClassTeacherEntity classTeacherEntity = classTeacherService.getClassTeacherById(id);
        studentEntity.setClassTeacherEntity(classTeacherEntity);
    });

    if (!model.containsAttribute("errorMessage")) {
        studentService.addOrUpdateStudent(studentEntity);
        redirectAttributes.addFlashAttribute("successMessage", "Student added successfully!");
        return "redirect:/students";
    } else {
        // If there's an error message related to date parsing, stay on the form page to show it
        return "add-student";
    }
}

private void prepareModelForForm(Model model) {
    List<ClassEntity> classes = classService.getAllClasses();
    List<ClassTeacherEntity> teachers = classTeacherService.getAllClassTeachers();
    model.addAttribute("classes", classes);
    model.addAttribute("teachers", teachers);
}

    @GetMapping("/{id}/edit")
    public String showEditStudentForm(@PathVariable Long id, Model model) {
        StudentEntity studentEntity = studentService.getStudentById(id);
        model.addAttribute("studentEntity", studentEntity);
        return "edit-student";
    }

    @PostMapping("/{id}/edit")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("studentEntity") StudentEntity studentEntity) {
        studentService.addOrUpdateStudent(studentEntity); // This will update the student entity
        return "redirect:/students";
    }
    

    @GetMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}

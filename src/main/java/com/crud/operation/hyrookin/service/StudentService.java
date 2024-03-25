package com.crud.operation.hyrookin.service;

import com.crud.operation.hyrookin.entity.StudentEntity;
import com.crud.operation.hyrookin.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    public StudentEntity addStudent(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }

    public StudentEntity updateStudent(Long id, StudentEntity studentEntity) {
        if (studentRepository.existsById(id)) {
            studentEntity.setId(id);
            return studentRepository.save(studentEntity);
        }
        return null; // Consider throwing a more specific error or exception
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public StudentEntity getStudentById(Long id) {
        Optional<StudentEntity> student = studentRepository.findById(id);
        if(student.isPresent()) {
            return student.get();
        } else {
            // It's generally a good idea to handle this case with a custom exception indicating the entity was not found
            throw new RuntimeException("Student not found with id " + id);
        }
    }
}

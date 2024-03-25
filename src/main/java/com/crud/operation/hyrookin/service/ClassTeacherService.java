package com.crud.operation.hyrookin.service;

import com.crud.operation.hyrookin.entity.ClassTeacherEntity;
import com.crud.operation.hyrookin.repository.ClassTeacherRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassTeacherService {

    @Autowired
    private ClassTeacherRepository classTeacherRepository;

    public List<ClassTeacherEntity> getAllClassTeachers() {
        return classTeacherRepository.findAll();
    }

    public ClassTeacherEntity addClassTeacher(ClassTeacherEntity classTeacherEntity) {
        return classTeacherRepository.save(classTeacherEntity);
    }

    public ClassTeacherEntity updateClassTeacher(Long id, ClassTeacherEntity classTeacherEntity) {
        if (classTeacherRepository.existsById(id)) {
            classTeacherEntity.setId(id);
            return classTeacherRepository.save(classTeacherEntity);
        }
        return null; // Handle error or throw exception
    }

    public void deleteClassTeacher(Long id) {
        classTeacherRepository.deleteById(id);
    }

  public ClassTeacherEntity getClassTeacherById(Long id) {
    return classTeacherRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("ClassTeacherEntity not found with id: " + id));
}
public Optional<ClassTeacherEntity> findById(Long id) {
    return classTeacherRepository.findById(id);
}
}

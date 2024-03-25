package com.crud.operation.hyrookin.service;

import com.crud.operation.hyrookin.entity.ClassEntity;
import com.crud.operation.hyrookin.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    public List<ClassEntity> getAllClasses() {
        return classRepository.findAll();
    }

    public ClassEntity addClass(ClassEntity classEntity) {
        return classRepository.save(classEntity);
    }

    public ClassEntity updateClass(Long id, ClassEntity classEntity) {
        if (classRepository.existsById(id)) {
            classEntity.setId(id);
            return classRepository.save(classEntity);
        }
        return null; // Handle error or throw exception
    }

    public void deleteClass(Long id) {
        classRepository.deleteById(id);
    }

    public ClassEntity getClassById(Long id) {
        Optional<ClassEntity> optionalClassEntity = classRepository.findById(id);
        return optionalClassEntity.orElse(null);
    }
}

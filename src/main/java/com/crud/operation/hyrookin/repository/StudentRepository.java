package com.crud.operation.hyrookin.repository;

import com.crud.operation.hyrookin.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}

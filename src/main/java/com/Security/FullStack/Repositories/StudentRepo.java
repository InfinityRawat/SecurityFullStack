package com.Security.FullStack.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Security.FullStack.Entity.StudentEntity;

public interface StudentRepo extends JpaRepository<StudentEntity, Integer> {

		Optional<StudentEntity> findStudentByEmail(String email);
}

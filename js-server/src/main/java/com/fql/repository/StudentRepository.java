package com.fql.repository;

import com.fql.entity.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentModel,Integer> {
}

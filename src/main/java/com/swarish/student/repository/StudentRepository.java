package com.swarish.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swarish.student.model.Student;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}

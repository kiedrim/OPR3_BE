package com.example.emmanager.repo;

import com.example.emmanager.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
    void deleteDepartmentById(Long id);

    Optional<Department> findDepartmentById(Long id);
}

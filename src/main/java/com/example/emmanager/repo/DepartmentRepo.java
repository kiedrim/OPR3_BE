package com.example.emmanager.repo;

import com.example.emmanager.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
    Department findDepartmentsById(Long id);
}

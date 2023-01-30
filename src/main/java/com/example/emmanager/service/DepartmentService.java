package com.example.emmanager.service;

import com.example.emmanager.model.Department;
import org.springframework.http.ResponseEntity;


public interface DepartmentService {

    Boolean existsByDepartmentId(Long depId);

    Department findDepartmentByDepartmentId(Long depId);

    ResponseEntity<Object> getAllDepartments();

    ResponseEntity<Department> getDepartmentById(Long depId);

    ResponseEntity<Department> addDepartment(Department department);

    ResponseEntity<Department> updateDepartment(Department department, Long depId);

    ResponseEntity<Object> deleteDepartment(Long id);

}

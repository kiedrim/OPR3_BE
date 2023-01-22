package com.example.emmanager.repo;

import com.example.emmanager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    void deleteEmployeeById(Long id);


    Employee findEmployeeById(Long empId);


    List<Employee> findAllByEmpDepartmentId(Long depId);
}

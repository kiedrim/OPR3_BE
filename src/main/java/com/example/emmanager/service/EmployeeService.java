package com.example.emmanager.service;
import com.example.emmanager.model.Employee;
import org.springframework.http.ResponseEntity;


public interface EmployeeService {

    Boolean existsByEmployeeId(Long employeeId);

    Employee findEmployeeByEmployeeId(Long employeeId);

    ResponseEntity<Object> getAllEmployees();

    ResponseEntity<Object> getAllEmployeesByDepartmentId(Long userId);

    ResponseEntity<Object> getEmployeeById(Long employeeId);

    ResponseEntity<Object> addEmployee(Employee employee, Long depId);

    ResponseEntity<Object> updateEmployee(Employee employee, Long employeeId);

    ResponseEntity<Object> deleteEmployee(Long employeeId);


}

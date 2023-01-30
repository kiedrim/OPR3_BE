package com.example.emmanager.service;
import com.example.emmanager.model.Employee;
import org.springframework.http.ResponseEntity;


public interface EmployeeService {

    ResponseEntity<Object> getAllEmployees();

    ResponseEntity<Employee> getEmployeeById(Long employeeId);

    ResponseEntity<Employee> addEmployee(Employee employee, Long depId);

    ResponseEntity<Employee> updateEmployee(Employee employee, Long employeeId);

    ResponseEntity<Object> deleteEmployee(Long employeeId);


}

package com.example.emmanager.controller;

import com.example.emmanager.model.Employee;
import com.example.emmanager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("all")
    public ResponseEntity<Object> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("get/{employeeId}")
    ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping(value = "/add/{depId}")
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee, @PathVariable("depId") Long depId){
        return employeeService.addEmployee(employee, depId);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee, @PathVariable("empId") Long empId){
        return employeeService.updateEmployee(employee,empId);
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("empId") Long empId){
        return employeeService.deleteEmployee(empId);
    }




}

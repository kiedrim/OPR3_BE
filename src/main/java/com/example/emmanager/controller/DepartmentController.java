package com.example.emmanager.controller;

import com.example.emmanager.model.Department;
import com.example.emmanager.service.DepartmentService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ComponentScan
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService=departmentService;
    }

    @GetMapping("all")
    public ResponseEntity<List<Department>> getAllEmployees(){
        List<Department> departments = departmentService.findAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Department> getEmployeeById(@PathVariable("id") Long id){
        Department department = departmentService.findDepartmentById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Department> addEmployee(@RequestBody Department department){
        Department newEmployee = departmentService.addDepartment(department);
        return  new ResponseEntity<>(newEmployee,HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Department> updateEmployee(@RequestBody Department department){
        Department updateEmployee = departmentService.updateDepartment(department);
        return  new ResponseEntity<>(updateEmployee,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Department> deleteEmployee(@PathVariable("id") Long id){
        departmentService.deleteDepartment(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}

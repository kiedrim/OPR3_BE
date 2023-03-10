package com.example.emmanager.controller;

import com.example.emmanager.model.Department;
import com.example.emmanager.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/departments/")
@CrossOrigin
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("all")
    ResponseEntity<Object> getAll(){
        return departmentService.getAllDepartments();
    }

    @GetMapping("get/{depId}")
    ResponseEntity<Department> getDepartmentById(@PathVariable("depId") Long depId) {
        return departmentService.getDepartmentById(depId);
    }

    @PostMapping(value = "add")
    ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }

    @PutMapping("edit/{depId}")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department newDepartment, @PathVariable("depId") Long depId){
        return departmentService.updateDepartment(newDepartment,depId);
    }

    @DeleteMapping(value = "delete/{depId}")
    ResponseEntity<?> deleteDepartment(@PathVariable("depId") Long depId) {
        return departmentService.deleteDepartment(depId);
    }

}

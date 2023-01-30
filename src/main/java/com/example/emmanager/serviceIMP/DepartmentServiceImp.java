package com.example.emmanager.serviceIMP;

import com.example.emmanager.model.Department;
import com.example.emmanager.repo.DepartmentRepo;
import com.example.emmanager.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImp implements DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public Boolean existsByDepartmentId(Long depId){
        return departmentRepo.existsById(depId);
    }

    @Override
    public Department findDepartmentByDepartmentId(Long depId) {
        return departmentRepo.findDepartmentsById(depId);
    }

    @Override
    public ResponseEntity<Object> getAllDepartments() {
        List<Department> ret = departmentRepo.findAll();
        ret.forEach(department -> {
            department.setEmployees(null);
        });

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Department> getDepartmentById(Long depId) {
        if (!departmentRepo.existsById(depId))
            return ResponseEntity.notFound().build();

        Department ret = departmentRepo.findDepartmentsById(depId);
        ret.setEmployees(null);

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Department> addDepartment(Department department) {
        departmentRepo.save(department);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Department> updateDepartment(Department newDepartment, Long depId) {
        if (!departmentRepo.existsById(depId))
            return ResponseEntity.notFound().build();

        Department department = departmentRepo.findDepartmentsById(depId);
        department.update(newDepartment);
        departmentRepo.save(department);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> deleteDepartment(Long depId) {
        if (!departmentRepo.existsById(depId))
            return ResponseEntity.notFound().build();

        Department department = departmentRepo.findDepartmentsById(depId);
        department.setEmployees(null);
        departmentRepo.delete(department);

        return ResponseEntity.ok().build();
    }

}

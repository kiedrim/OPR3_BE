package com.example.emmanager.service;

import com.example.emmanager.exception.UserNotFoundException;
import com.example.emmanager.model.Department;
import com.example.emmanager.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepartmentService {
    private final DepartmentRepo departmentRepo;

    @Autowired
    public DepartmentService(DepartmentRepo departmentRepo){
        this.departmentRepo = departmentRepo;
    }

    public Department addDepartment(Department department){
        return departmentRepo.save(department);
    }

    public List<Department> findAllDepartments(){
        return  departmentRepo.findAll();
    }
    public Department updateDepartment(Department department){
        return departmentRepo.save(department);
    }

    public Department findDepartmentById(Long id){
        return departmentRepo.findDepartmentById(id)
                .orElseThrow(() -> new UserNotFoundException("Department by id" + id + "was not found"));
    }

    public void deleteDepartment(Long id){
        departmentRepo.deleteDepartmentById(id);
    }

}

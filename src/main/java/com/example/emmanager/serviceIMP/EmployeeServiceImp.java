package com.example.emmanager.serviceIMP;

import com.example.emmanager.model.Department;
import com.example.emmanager.model.Employee;
import com.example.emmanager.model.Project;
import com.example.emmanager.repo.EmployeeRepo;
import com.example.emmanager.service.DepartmentService;
import com.example.emmanager.service.EmployeeService;
import com.example.emmanager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceImp implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ProjectService projectService;

    @Override
    public ResponseEntity<Object> getAllEmployees() {
        List<Employee> ret = employeeRepo.findAll();

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(Long empId) {
        if (!employeeRepo.existsById(empId))
            return ResponseEntity.notFound().build();

        Employee ret = employeeRepo.findEmployeeById(empId);
        ret.setProjects(null);
        ret.setEmpDepartment(null);

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Employee> addEmployee(Employee employee, Long depId) {
        if (!departmentService.existsByDepartmentId(depId))
            return ResponseEntity.notFound().build();

        Department department = departmentService.findDepartmentByDepartmentId(depId);

        Set<Project> foundProjects = new HashSet<>();
        for (Project project : employee.getProjects()) {
            System.out.println(project.toString());
            projectService.getProjectById(project.getId());
            foundProjects.add(project);
        }

        employee.setProjects(foundProjects);
        employee.setEmpDepartment(department);

        employeeRepo.save(employee);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Employee> updateEmployee(Employee newEmployee, Long empId) {
        if (!employeeRepo.existsById(empId))
            return ResponseEntity.notFound().build();

        Employee employee = employeeRepo.findEmployeeById(empId);
        employee.update(newEmployee);
        employeeRepo.save(employee);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> deleteEmployee(Long empId) {
        if (!employeeRepo.existsById(empId))
            return ResponseEntity.notFound().build();

        Employee employee = employeeRepo.findEmployeeById(empId);
        employee.setEmpDepartment(null);
        employee.setProjects(null);
        employeeRepo.delete(employee);

        return ResponseEntity.ok().build();
    }

}

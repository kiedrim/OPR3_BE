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
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImp implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ProjectService projectService;

    @Override
    public Boolean existsByEmployeeId(Long empId) {
        return employeeRepo.existsById(empId);
    }

    @Override
    public Employee findEmployeeByEmployeeId(Long empId) {
        return employeeRepo.findEmployeeById(empId);
    }

    @Override
    public ResponseEntity<Object> getAllEmployees() {
        List<Employee> ret = employeeRepo.findAll();
        /*ret.forEach(employee -> {
            employee.setEmpDepartment(null);
            employee.setProjects(null);
        });*/

        return ResponseEntity.ok(ret);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> getAllEmployeesByDepartmentId(Long depId) {
        if (!departmentService.existsByDepartmentId(depId))
            return ResponseEntity.notFound().build();

        List<Employee> employees = employeeRepo.findAllByEmpDepartmentId(depId)
                .stream()
                .map(employee -> {
                    employee.setProjects(employee.getProjects());
                    return employee;
                }).collect(Collectors.toList());
        return ResponseEntity.ok(employees);
    }

    @Override
    public ResponseEntity<Object> getEmployeeById(Long empId) {
        if (!employeeRepo.existsById(empId))
            return ResponseEntity.notFound().build();

        Employee ret = employeeRepo.findEmployeeById(empId);
        ret.setProjects(null);
        ret.setEmpDepartment(null);

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Object> addEmployee(Employee employee, Long depId) {
        if (!departmentService.existsByDepartmentId(depId))
            return ResponseEntity.notFound().build();

        Department department = departmentService.findDepartmentByDepartmentId(depId);


        System.out.println(department.toString());

        Set<Project> foundProjects = new HashSet<>();
        for (Project project : employee.getProjects()) {
            System.out.println(project.toString());
            projectService.getProjectById(project.getId());
            foundProjects.add(project);
        }

        System.out.println(foundProjects.toString());
        employee.setProjects(foundProjects);
        employee.setEmpDepartment(department);

        employeeRepo.save(employee);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> updateEmployee(Employee newEmployee, Long empId) {
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

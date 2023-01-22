package com.example.emmanager.model;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "employee_id")
    private Long id;

    private String name;
    private String email;
    private String jobTitle;
    private String phone;
    private String imageUrl;
    private String employeeCode;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dep_id", nullable = true)
    private Department empDepartment;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "project_employee",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Project> projects;

    public void update(Employee newEmployee) {
        setName(newEmployee.getName());
        setEmail(newEmployee.getEmail());
        setJobTitle(newEmployee.getJobTitle());
        setPhone(newEmployee.getPhone());
        setImageUrl(newEmployee.getImageUrl());
        setEmpDepartment(newEmployee.getEmpDepartment());
        setProjects(newEmployee.getProjects());
    }
}

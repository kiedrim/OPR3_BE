package com.example.emmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dep_id")
    private Long id;
    private String depName;



    @OneToMany(mappedBy = "empDepartment", cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Employee> employees;

    public void update(Department newDepartment){
        setDepName(newDepartment.getDepName());
        setEmployees(newDepartment.getEmployees());
    }
}

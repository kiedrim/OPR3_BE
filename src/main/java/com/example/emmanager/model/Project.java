package com.example.emmanager.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id")
    private Long id;

    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "projects"
    )
    @JsonIgnore
    private Set<Employee> employees;

    private String projectName;

    public void update(Project newProject){
        setProjectName(newProject.getProjectName());
    }




}

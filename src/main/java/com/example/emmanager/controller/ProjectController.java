package com.example.emmanager.controller;

import com.example.emmanager.model.Project;
import com.example.emmanager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/all")
    ResponseEntity<Object> getAll(){
        return projectService.getAllProjects();
    }

    @GetMapping("get/{projectId}")
    ResponseEntity<Project> getProjectById(@PathVariable("projectId") Long proId){
        return projectService.getProjectById(proId);
    }

    @PostMapping("/add")
    public ResponseEntity<Project> addProject(@RequestBody Project project){
        return projectService.addProject(project);
    }

    @PutMapping("/update/{proId}")
    public ResponseEntity<Project> updateProject(@RequestBody Project project, @PathVariable("proId") Long proId){
        return projectService.updateProject(project, proId);
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Long id){
        return projectService.deleteProject(id);
    }
}

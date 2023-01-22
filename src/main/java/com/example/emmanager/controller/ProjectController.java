package com.example.emmanager.controller;

import com.example.emmanager.model.Project;
import com.example.emmanager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    ResponseEntity<Object> getProjectById(@PathVariable("projectId") Long proId){
        return projectService.getProjectById(proId);
    }

    @GetMapping("/find/{id}")
    ResponseEntity<Object> findProjectById(@PathVariable("id") Long id){
        return new ResponseEntity<>(projectService.findProjectByProjectId(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addProject(@RequestBody Project project){
        return projectService.addProject(project);
    }

    @PutMapping("/update/{proId}")
    public ResponseEntity<Object> updateProject(@RequestBody Project project, @PathVariable("proId") Long proId){
        return projectService.updateProject(project, proId);
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Long id){
        Project project = projectService.findProjectByProjectId(id);
        project.setEmployees(null);
        return new ResponseEntity<>(projectService.deleteProject(id),HttpStatus.OK);
    }
}

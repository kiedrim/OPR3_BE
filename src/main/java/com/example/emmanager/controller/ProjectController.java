package com.example.emmanager.controller;

import com.example.emmanager.model.Project;
import com.example.emmanager.service.ProjectService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ComponentScan
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }
    @GetMapping("all")
    public ResponseEntity<List<Project>> getAllProjects(){
        List<Project> Projects =projectService.findAllProjects();
        return new ResponseEntity<>(Projects, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") Long id){
        Project Project = projectService.findProjectById(id);
        return new ResponseEntity<>(Project, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Project> addProject(@RequestBody Project Project){
        Project newProject = projectService.addProject(Project);
        return new ResponseEntity<>(newProject, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Project> updateProject(@RequestBody Project Project){
        Project updateProject = projectService.updateProject(Project);
        return new ResponseEntity<>(updateProject, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Long id){
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

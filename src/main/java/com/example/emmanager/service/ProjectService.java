package com.example.emmanager.service;

import com.example.emmanager.model.Project;
import org.springframework.http.ResponseEntity;

public interface ProjectService {

    Project findProjectByProjectId(Long proId);

    ResponseEntity<Object>getAllProjects();

    ResponseEntity<Project>getProjectById(Long id);

    ResponseEntity<Object>addProject(Project newProject);

    ResponseEntity<Object>updateProject(Project updatedProject, Long proId);

    ResponseEntity<Object>deleteProject(Long proId);

}

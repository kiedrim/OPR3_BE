package com.example.emmanager.service;

import com.example.emmanager.exception.UserNotFoundException;
import com.example.emmanager.model.Job;
import com.example.emmanager.model.Project;
import com.example.emmanager.repo.JobRepo;
import com.example.emmanager.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectService {
    private final ProjectRepo projectRepo;

    @Autowired
    public ProjectService(ProjectRepo projectRepo){
        this.projectRepo = projectRepo;
    }

    public Project addProject(Project project){
        return projectRepo.save(project);
    }

    public List<Project> findAllProjects(){
        return  projectRepo.findAll();
    }

    public Project updateProject(Project project){
        return projectRepo.save(project);
    }

    public Project findProjectById(Long id){
        return projectRepo.findProjectById(id)
                .orElseThrow(() -> new UserNotFoundException("Project by id" + id + "was not found"));
    }

    public void deleteProject(Long id){
        projectRepo.deleteProjectById(id);
    }
}

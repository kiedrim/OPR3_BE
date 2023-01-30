package com.example.emmanager.serviceIMP;

import com.example.emmanager.model.Project;
import com.example.emmanager.repo.ProjectRepo;
import com.example.emmanager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectServiceImp implements ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    @Override
    public Project findProjectByProjectId(Long proId) {
        return projectRepo.findProjectById(proId);
    }

    @Override
    public ResponseEntity<Object> getAllProjects() {
        List<Project> ret = projectRepo.findAll();
        ret.forEach(project -> {
            project.setEmployees(null);
        });

        return ResponseEntity.ok(ret);
    }


    @Override
    public ResponseEntity<Project> getProjectById(Long proId) {
        if (!projectRepo.existsById(proId))
            return ResponseEntity.notFound().build();

        Project ret = projectRepo.findProjectById(proId);
        ret.setEmployees(null);

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Object> addProject(Project project) {
        projectRepo.save(project);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> updateProject(Project newProject, Long proId) {
        if (!projectRepo.existsById(proId))
            return ResponseEntity.notFound().build();

        Project project = projectRepo.findProjectById(proId);
        project.update(newProject);
        projectRepo.save(project);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> deleteProject(Long proId) {
        if (!projectRepo.existsById(proId))
            return ResponseEntity.notFound().build();

        Project project = projectRepo.findProjectById(proId);
        project.setEmployees(null);
        projectRepo.delete(project);

        return ResponseEntity.ok().build();
    }

}

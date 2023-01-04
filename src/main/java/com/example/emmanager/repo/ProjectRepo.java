package com.example.emmanager.repo;

import com.example.emmanager.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface ProjectRepo extends JpaRepository<Project, Long> {
    void deleteProjectById(Long id);

    Optional<Project> findProjectById(Long id);
}

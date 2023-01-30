package com.example.emmanager.repo;

import com.example.emmanager.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {
    Project findProjectById(Long id);
}

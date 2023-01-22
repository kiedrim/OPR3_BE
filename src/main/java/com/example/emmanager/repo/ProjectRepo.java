package com.example.emmanager.repo;

import com.example.emmanager.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {
    void deleteProjectById(Long id);

    Project findProjectById(Long id);
}

package com.example.emmanager.service;

import com.example.emmanager.exception.UserNotFoundException;
import com.example.emmanager.model.Job;
import com.example.emmanager.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    private final JobRepo jobRepo;

    @Autowired
    public JobService(JobRepo jobRepo){
        this.jobRepo = jobRepo;
    }

    public Job addJob(Job job){
        return jobRepo.save(job);
    }

    public List<Job> findAllJobs(){
        return  jobRepo.findAll();
    }
    public Job updateJob(Job job){
        return jobRepo.save(job);
    }

    public Job findJobById(Long id){
        return jobRepo.findJobById(id)
                .orElseThrow(() -> new UserNotFoundException("Job by id" + id + "was not found"));
    }

    public void deleteJob(Long id){
        jobRepo.deleteJobById(id);
    }
}

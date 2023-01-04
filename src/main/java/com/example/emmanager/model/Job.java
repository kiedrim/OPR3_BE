package com.example.emmanager.model;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class Job implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String jobName;
    private int jobSalary;

    public Job() {
    }

    public Job(Long id, String jobName, int jobSalary) {
        this.id = id;
        this.jobName = jobName;
        this.jobSalary = jobSalary;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", jobName='" + jobName + '\'' +
                ", jobSalary=" + jobSalary +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setJobSalary(int jobSalary) {
        this.jobSalary = jobSalary;
    }

    public Long getId() {
        return id;
    }

    public String getJobName() {
        return jobName;
    }

    public int getJobSalary() {
        return jobSalary;
    }
}

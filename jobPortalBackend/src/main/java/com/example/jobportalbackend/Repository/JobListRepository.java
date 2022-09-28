package com.example.jobportalbackend.Repository;

import com.example.jobportalbackend.model.JobList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobListRepository extends MongoRepository<JobList,String> {
}

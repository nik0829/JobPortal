package com.example.jobportalbackend.controller;

import com.example.jobportalbackend.Repository.JobListRepository;
import com.example.jobportalbackend.Repository.SearchJobList;
import com.example.jobportalbackend.model.JobList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class JobListController {

    @Autowired
    JobListRepository jobListRepository;

    @Autowired
    SearchJobList searchJobList;

    @GetMapping("/allPosts")
    @CrossOrigin
    public List<JobList> getAllPosts()
    {
        log.info("get all jobs...");
        return jobListRepository.findAll();
    }

    @GetMapping("/posts/{text}")
    @CrossOrigin
    public List<JobList> search(@PathVariable String text)
    {
        log.info("searching...");
        return searchJobList.findByText(text);
    }

    @PostMapping("/post")
    @CrossOrigin
    public JobList addPost(@RequestBody JobList post)
    {
        log.info("adding new record...");
        return jobListRepository.save(post);
    }
}

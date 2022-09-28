package com.example.jobportalbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "JobList")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobList {

    private String title;
    private String description;
    private int experience;
    private String[] technologies;
}

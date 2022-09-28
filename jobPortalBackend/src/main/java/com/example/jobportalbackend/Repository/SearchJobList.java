package com.example.jobportalbackend.Repository;

import com.example.jobportalbackend.model.JobList;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class SearchJobList {

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    public List<JobList> findByText(String text) {

        log.info("searching with key word : "+ text);
        final List<JobList> posts = new ArrayList<>();

        log.info("entering...");
        MongoDatabase database = client.getDatabase("JobListingPortal");
        System.out.println(database);
        MongoCollection<Document> collection = database.getCollection("JobList");

        System.out.println(collection);
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("text",
                                new Document("query", text)
                                        .append("path", Arrays.asList("technologies", "description", "title")))),
                new Document("$sort",
                        new Document("exp", 1L)),
                new Document("$limit", 5L)));
        System.out.println(result.toString());

        System.out.println(collection);

        result.forEach(doc -> posts.add(converter.read(JobList.class,doc)));
        System.out.println(posts);
        log.info(posts.toString());
        log.info("exiting...");
        return posts;
    }
}

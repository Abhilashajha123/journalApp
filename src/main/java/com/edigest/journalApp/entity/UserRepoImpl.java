package com.edigest.journalApp.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserRepoImpl {


    @Autowired
    private MongoTemplate mongoTemplate;


    public List<User> getUserForSA(){
        Query query = new Query();

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

       query.addCriteria(Criteria.where("email").regex(emailRegex));
       query.addCriteria(Criteria.where("sentimentAnalysis").exists(true));


        List<User> users = mongoTemplate.find(query, User.class);

        return users;
    }

}

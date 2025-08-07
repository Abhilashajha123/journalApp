package com.edigest.journalApp.service;


import com.edigest.journalApp.entity.UserRepoImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserRepoImplTest {


    @Autowired
    private UserRepoImpl userRepoImpl;

    @Disabled("Temporarily disabled due to MongoDB connection issue")
    @Test
    public void testSaveUser(){

        userRepoImpl.getUserForSA();

    }

}

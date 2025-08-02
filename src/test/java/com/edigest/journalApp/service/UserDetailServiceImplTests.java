package com.edigest.journalApp.service;


import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;


public class UserDetailServiceImplTests {

    @InjectMocks
    private CustomUserDetailServiceImpl userDetailServiceImpl;

    @Mock
    private UserRepo userRepo;



    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Disabled("Temporarily disabled for debugging")
    @Test
    public void loadUserByUserNameTest(){

        when(userRepo.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("aam").password("aam").roles(new ArrayList<>()).build());

        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername("aam");
        Assertions.assertNotNull(userDetails);

    }
}

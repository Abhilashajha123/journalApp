package com.edigest.journalApp.service;

import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {


    @Autowired
    private UserService userService;



    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){
        assertTrue(userService.saveNewEntry(user));
    }



    //@ValueSource(strings = {
//
//            "abhi",
//            "abhishek",
//            "raviguptaa",
//            "hongkong"
//    })
//    public void testFindBuUserName(String name){
//        assertNotNull(userRepo.findByUserName(name));
//    }


    @Disabled
    @ParameterizedTest
    @CsvSource({

            "1,1,2",
            "2,10,12",
            "3,3,5"
    })
    public void test(int a, int b , int expected){

        assertEquals(expected, a+b);

    }
}

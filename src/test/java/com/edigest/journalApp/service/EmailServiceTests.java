package com.edigest.journalApp.service;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled("Temporarily disabled due to MongoDB connection issue") // <--- Disable entire class
@SpringBootTest
public class EmailServiceTests {


    @Autowired
    private EmailService emailService;


    @Test
    void sendEmail(){
        emailService.sendEmail("abhilashakumari455@gmail.com","Testing java mail sender","hi,how are you");

    }
}

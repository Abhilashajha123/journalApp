package com.edigest.journalApp.controller;


import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.service.JournalEntryService;
import com.edigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournlEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

   @GetMapping
   public ResponseEntity<?> getAllJournalEntryOfUser(){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntryList();

        if(all!=null && !all.isEmpty()){
            return  new ResponseEntity<>(all,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    };

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry){
       try{
           Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           String userName = authentication.getName();
           journalEntryService.saveEntry(entry,userName);
           return new ResponseEntity<>(entry, HttpStatus.CREATED);
       }catch (Exception e){
           return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
   }

   @GetMapping("id/{myid}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myid){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String userName = authentication.getName();

       User user = userService.findByUserName(userName);
       List<JournalEntry> journalEntries = user.getJournalEntryList().stream().filter(x->x.getId().equals(myid)).collect(Collectors.toList());
       if(!journalEntries.isEmpty()){
           Optional<JournalEntry> journalEntry = journalEntryService.findById(myid);
           if(journalEntry.isPresent()){
               return  new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
           }
       }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

    @DeleteMapping("id/{myid}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
      boolean removed = journalEntryService.deleteById(myid, userName);
      if(removed){
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }else{
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }


    @PutMapping("id/{myid}")
    public ResponseEntity<?> putJournalEntryById(@PathVariable ObjectId myid, @RequestBody JournalEntry entry){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User user = userService.findByUserName(userName);

        List<JournalEntry> journalEntries = user.getJournalEntryList().stream().filter(x->x.getId().equals(myid)).collect(Collectors.toList());

        if(!journalEntries.isEmpty()){

                Optional<JournalEntry> journalEntry = journalEntryService.findById(myid);

                if(journalEntry.isPresent()){
                    JournalEntry oldJournalEntry = journalEntry.get();
                    oldJournalEntry.setTitle(entry.getTitle()!=null && !entry.getTitle().equals("") ? entry.getTitle() : oldJournalEntry.getTitle());
                    oldJournalEntry.setContent(entry.getContent()!=null && !entry.getContent().equals("") ? entry.getContent()  : oldJournalEntry.getContent());
                    journalEntryService.saveEntry(oldJournalEntry);
                    return new ResponseEntity<>(oldJournalEntry,HttpStatus.OK);

                }
        }

       return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}

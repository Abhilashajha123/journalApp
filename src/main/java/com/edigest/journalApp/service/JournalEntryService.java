package com.edigest.journalApp.service;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.JournalEntryRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService  {

   @Autowired
    private JournalEntryRepo journalEntryRepo;


   //private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

   @Autowired
   private UserService userService;

   @Transactional
   public void saveEntry(JournalEntry journalEntry, String userName){
       try {
           User user = userService.findByUserName(userName);
           journalEntry.setDate(LocalDateTime.now());
           JournalEntry journalEntrySavedData = journalEntryRepo.save(journalEntry);
           user.getJournalEntryList().add(journalEntrySavedData);
           userService.saveUser(user);
       }catch (Exception e){
            log.info("hahahahaha");
            throw new RuntimeException("An error occured while saving entry");
       }
   }

    public void saveEntry(JournalEntry journalEntry){
       journalEntryRepo.save(journalEntry);

    }


   public List<JournalEntry> getAll(){

       return journalEntryRepo.findAll();
   }

    public Optional<JournalEntry> findById(ObjectId id){

        return journalEntryRepo.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed = false;
       try{
           User user = userService.findByUserName(userName);
           removed = user.getJournalEntryList().removeIf(x->x.getId().equals(id));
           if(removed){
               userService.saveNewEntry(user);
               journalEntryRepo.deleteById(id);
           }
       } catch (Exception e) {
           log.info("Error {} ", e);
           throw new RuntimeException("an error occured while deleting the entry");
       }

        return removed;
    }


}

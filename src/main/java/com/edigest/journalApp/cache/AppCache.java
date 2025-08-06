package com.edigest.journalApp.cache;

import com.edigest.journalApp.entity.ConfigJournalAppEntity;
import com.edigest.journalApp.repository.ConfigJournalAppRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{

        WEATHER_API
    }


    @Autowired
    private ConfigJournalAppRepo configJournalAppRepo;

    public Map<String,String> appCache;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepo.findAll();

        for(ConfigJournalAppEntity configJournalAppEntity : all){

            appCache.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
        }

    }
}

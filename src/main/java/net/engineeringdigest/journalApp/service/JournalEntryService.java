package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
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
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){

        try {
            User byUserName = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry savedJournalEntries = journalEntryRepository.save(journalEntry);
            byUserName.getJournalEntries().add(savedJournalEntries);
            userService.saveUser(byUserName);
        } catch (Exception e) {
            throw new RuntimeException("An error happened while saving the entry",e);
//            log.error("Exception",e);
        }
    }

    public void saveEntry(JournalEntry journalEntry){

        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){

        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){

        boolean removed = false;
        try {
            User byUserName = userService.findByUserName(userName);
            removed = byUserName.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userService.saveUser(byUserName);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    return removed;
    }

//    public List<JournalEntry> findByUserName(String userName){
//
//        User byUserName = userService.findByUserName(userName);
//
//    }
}

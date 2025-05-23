package com.example.JournalEntry.Service;

import com.example.JournalEntry.Entity.JournalEntry;
import com.example.JournalEntry.Entity.User;
import com.example.JournalEntry.Repository.JournalEntryRepository;
import com.example.JournalEntry.Repository.UserRepository;
import org.bson.types.ObjectId;
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
    public void saveEntry(JournalEntry journalEntry,String userName){
        try {
            User user=userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved=journalEntryRepository.save(journalEntry);
            user.getJournalEntryList().add(saved);
            userService.saveEntry(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("Error:",e);
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId Id){
        return journalEntryRepository.findById(Id);
    }

    public void deleteById(ObjectId Id,String userName){
        User user=userService.findByUserName(userName);
        user.getJournalEntryList().removeIf(x -> x.getId().equals(Id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(Id);
    }

}

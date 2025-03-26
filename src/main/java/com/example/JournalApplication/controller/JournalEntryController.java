package com.example.JournalApplication.controller;

import com.example.JournalApplication.Entity.JournalEntry;
import com.example.JournalApplication.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        System.out.printf("Hello");
        return journalEntryService.getAll();
    }
    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry journalEntry){
        journalEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(journalEntry);
        return journalEntry;
    }
    @GetMapping("id/{myd}")
    public JournalEntry findById(@PathVariable ObjectId myId ){
        return journalEntryService.findById(myId).orElse(null);
    }
    @DeleteMapping("id/{myId}")
    public boolean deleteById(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return true;
    }
//    @PutMapping
//    public JournalEntry update(@PathVariable ObjectId myId,@RequestBody JournalEntry journalEntry){
//        return journalEntries.put(myId,journalEntry);
//    }
}

package com.example.JournalEntry.Service;

import com.example.JournalEntry.Entity.JournalEntry;
import com.example.JournalEntry.Entity.User;
import com.example.JournalEntry.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User userName){
        userRepository.save(userName);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId Id){
        return userRepository.findById(Id);
    }

    public void deleteById(ObjectId Id){
        userRepository.deleteById(Id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}

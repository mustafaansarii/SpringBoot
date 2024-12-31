package com.journalapp.journal.APP.service;

import com.journalapp.journal.APP.entity.User;
import com.journalapp.journal.APP.reposeritory.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class userEntryService {
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Save user with encrypted password
    public void saveEntry(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // Encrypt password
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }

    // Save new user (with encoded password)
    public void saveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // Encrypt password
        userRepository.save(user);
    }

    // Get all users
    public List<User> getAll() {
        return userRepository.findAll();
    }

    // Find user by ID
    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    // Delete user by ID
    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

    // Find user by username
    public User findbyusername(String userName) {
        return userRepository.findbyusername(userName);
    }
}


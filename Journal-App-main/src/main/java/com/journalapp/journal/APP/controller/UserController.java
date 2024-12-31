package com.journalapp.journal.APP.controller;

import com.journalapp.journal.APP.entity.User;
import com.journalapp.journal.APP.service.userEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private userEntryService userEntryService;

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAlluser() {
        List<User> users = userEntryService.getAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Create a new user
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        userEntryService.saveEntry(user);  // Saving user without encoding password
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Update user details
    @PutMapping("/{userName}")
    public ResponseEntity<Void> UserUpdate(@RequestBody User user, @PathVariable String userName) {
        User userInDB = userEntryService.findbyusername(userName);
        if (userInDB != null) {
            userInDB.setUserName(user.getUserName());

            // Only update password if it's provided
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                userInDB.setPassword(user.getPassword());  // Directly saving password as plain text
            }
            userEntryService.saveEntry(userInDB);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

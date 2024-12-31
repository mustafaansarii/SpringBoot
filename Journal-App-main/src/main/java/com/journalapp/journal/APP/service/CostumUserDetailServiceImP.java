package com.journalapp.journal.APP.service;

import com.journalapp.journal.APP.entity.User;
import com.journalapp.journal.APP.reposeritory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CostumUserDetailServiceImP implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the database
        User user = userRepository.findbyusername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Log the fetched user details (for debugging purposes)
        System.out.println("Found user: " + user.getUserName());

        // Convert User entity to Spring Security's UserDetails object
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder = org.springframework.security.core.userdetails.User.builder();
        return userBuilder
                .username(user.getUserName())  // Set username
                .password(user.getPassword()) // Set encrypted password
                .roles(user.getRoles().toArray(new String[0])) // Convert roles to array
                .build();
    }
}

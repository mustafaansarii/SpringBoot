package com.journalapp.journal.APP.entity;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "user")
@Data
public class User {
    @Id
    private String id; // Use String for simplicity, MongoDB ObjectId can also be used

    @Indexed(unique = true)  // Ensuring username is unique
    @NonNull
    @JsonProperty("username")
    private String userName;

    @NonNull
    @JsonProperty("password")
    private String password;

    @DBRef  // Creating a reference to the journal entries
    private List<journalEntrry> journalEntries = new ArrayList<>();  // Ensure correct class name (capitalized)

    private List<String> roles;

    // You can also add a constructor if needed
    public User(String userName, String password, List<String> roles) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public <E> List<E> getJournalEntries() {
        Object List = null;
        return (java.util.List<E>) List;
    }
}

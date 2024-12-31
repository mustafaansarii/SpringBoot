package com.journalapp.journal.APP.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class journalEntrry {
    @Id
    private ObjectId id;

    @NonNull
    private String title;

    private String content;

    private LocalDateTime date = LocalDateTime.now();  // Automatically set to current time when the entry is created

    // Constructor without id to be used when creating new entries
    public journalEntrry(String title, String content) {
        this.title = title;
        this.content = content;
        this.date = LocalDateTime.now();  // Set current time for new entries
    }

    public void setDate(LocalDateTime now) {
    }
}

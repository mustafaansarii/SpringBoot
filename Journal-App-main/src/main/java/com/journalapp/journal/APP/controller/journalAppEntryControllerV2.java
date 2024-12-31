package com.journalapp.journal.APP.controller;

import com.journalapp.journal.APP.entity.User;
import com.journalapp.journal.APP.service.journalEntryService;
import com.journalapp.journal.APP.entity.journalEntrry;
import com.journalapp.journal.APP.service.userEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class journalAppEntryControllerV2 {
    @Autowired
    private journalEntryService journalEntryService;
    @Autowired
    private userEntryService userEntryService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAlljournalAppEntriesofUser(@PathVariable String userName) {
        User user = userEntryService.findbyusername(userName);
        List<journalEntrry> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{userName}")
    public ResponseEntity<journalEntrry> createEntry(@RequestBody journalEntrry myEntry, @PathVariable String userName) {
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myid}")
    public ResponseEntity<journalEntrry> getJournalEntryById(@PathVariable ObjectId myid) {
        Optional<journalEntrry> journalEntrry = journalEntryService.findById(myid);
        if (journalEntrry.isPresent()) {
            return new ResponseEntity<>(journalEntrry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    @DeleteMapping("/id/{userName}/{myid}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId myid, @PathVariable String userName) {
        journalEntryService.deleteById(myid, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity<journalEntrry> updateJournalEntry(
            @PathVariable ObjectId id,
            @RequestBody journalEntrry newEntry,
            @PathVariable String userName
    ) {
        Optional<journalEntrry> optionalOldEntry = journalEntryService.findById(id);

        if (optionalOldEntry.isPresent()) {
            journalEntrry oldEntry = optionalOldEntry.get();

            if (newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()) {
                oldEntry.setTitle(newEntry.getTitle());
            }
            if (newEntry.getContent() != null && !newEntry.getContent().isEmpty()) {
                oldEntry.setContent(newEntry.getContent());
            }
            oldEntry.setDate(newEntry.getDate() != null ? newEntry.getDate() : oldEntry.getDate());

            journalEntryService.saveEntry(oldEntry, userName);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

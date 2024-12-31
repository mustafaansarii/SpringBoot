package com.journalapp.journal.APP.service;

import com.journalapp.journal.APP.entity.User;
import com.journalapp.journal.APP.entity.journalEntrry;
import com.journalapp.journal.APP.reposeritory.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class journalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private userEntryService userEntryService;
    @Transactional
    public void saveEntry(journalEntrry entry, String userName) {
    try {
        User user = userEntryService.findbyusername(userName);
        entry.setDate(LocalDateTime.now());
        journalEntrry saved = journalEntryRepository.save(entry);
        user.getJournalEntries().add(saved);
        userEntryService.saveEntry(user); // Save the updated user with the new entry
    }
    catch (Exception e){
        throw  new RuntimeException("An Error occurd while savinf Entries", e);
    }
    }

    public List<journalEntrry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<journalEntrry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName) {
        User user = userEntryService.findbyusername(userName);
        user.getJournalEntries().removeIf(x -> false);
        userEntryService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }
}

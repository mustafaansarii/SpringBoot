//package com.journalapp.journal.APP.controller;
//
//import com.journalapp.journal.APP.entity.journalEntrry;  // Corrected import statement
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//
//@RestController
//@RequestMapping("/journal")
//public class journalAppEntry {
//
//    private Map<Long, journalEntrry> journalAppEntries = new HashMap<>();
//
//    @GetMapping
//    public List<journalEntrry> getAll() {
//        return new ArrayList<>(journalAppEntries.values());
//    }
//
//    @PostMapping
//    public boolean createEntry(@RequestBody journalEntrry myEntry) {
//        journalAppEntries.put(myEntry.getId(), myEntry);
//        return true;
//    }
//    @GetMapping("/id/{myid}")
//    public journalEntrry journalentrry(@PathVariable Long myid){
//
//        return journalAppEntries.get(myid);
//    }
//    @DeleteMapping("/id/{myid}")
//    public journalEntrry deletejournalentrry(@PathVariable Long myid)
//    {
//        return journalAppEntries.remove(myid);
//    }
//    @PutMapping("id/{id}")
//    public journalEntrry updatejournalid(@PathVariable Long id, @RequestBody journalEntrry myEntry){
//        return journalAppEntries.put(id,myEntry);
//    }
//}
//

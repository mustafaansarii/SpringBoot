package com.journalapp.journal.APP.reposeritory;
import com.journalapp.journal.APP.entity.journalEntrry;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import java.util.jar.JarEntry;


public interface JournalEntryRepository extends MongoRepository<journalEntrry, ObjectId> {
}


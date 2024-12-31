package com.journalapp.journal.APP.reposeritory;

import com.journalapp.journal.APP.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    @Query("{ 'userName' : ?0 }")
    User findbyusername(String userName);
}

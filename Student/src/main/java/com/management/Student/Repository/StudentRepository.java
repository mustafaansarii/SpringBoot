package com.management.Student.Repository;


import com.management.Student.Entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//
//public interface StudentRepository extends JpaRepository<Student, Long> {
//}


@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    // You can define custom queries here if needed
}
package com.management.Student.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "students") // Specifies the collection name in MongoDB
public class Student {

    @Id
    private String studentId;  // MongoDB generates ObjectId as String

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String email;
}
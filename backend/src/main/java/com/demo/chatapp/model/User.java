package com.demo.chatapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "users")
public class User {

    @Id
    String id;

    String username;

    String password;

    String email;

    String profilePictureUrl;

    String roles;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;
}

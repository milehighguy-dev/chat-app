package com.demo.chatapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    String username;

    String password;

    String roles;
}

package com.demo.chatapp.service;

import com.demo.chatapp.model.User;
import com.demo.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        return user;
    }

}

package com.demo.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.demo.chatapp.service.UserService;
import com.demo.chatapp.model.User;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    UserService userService;
    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        //return 200 even if empty list
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    ResponseEntity<User> getUser(@PathVariable String userId) {

        Optional<User> user = userService.getUser(userId);

        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/{userId}")
    ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User user) {
        Optional<User> existingUser = userService.getUser(userId);
        if (existingUser.isPresent()) {
            user.setId(userId);
            User updatedUser = userService.saveUser(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return  ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);

    }

    @DeleteMapping("/{userId}")
    ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        Optional<User> existingUser = userService.getUser(userId);
        if (existingUser.isPresent()) {
            userService.deleteUser(userId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

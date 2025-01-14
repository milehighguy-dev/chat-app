package com.demo.chatapp.repository;

import com.demo.chatapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByUserName(String userName);

    boolean existsByEmail(String email);
}

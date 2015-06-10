package com.swordssorcery.server.model.repository;

import com.swordssorcery.server.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>, CustomUserRepository {

}
package com.swordssorcery.server.model.db.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDatabaseEntity, String>, CustomUserRepository {

    UserDatabaseEntity findByUsername(String username);

    UserDatabaseEntity findByEmail(String email);

    UserDatabaseEntity findByUsernameAndPassword(String username, String password);
}
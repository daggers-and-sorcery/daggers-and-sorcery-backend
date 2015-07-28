package com.swordssorcery.server.model.db.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDatabaseEntity, String> {

    UserDatabaseEntity findByUsername(String username);

    UserDatabaseEntity findByEmail(String email);

    UserDatabaseEntity findByUsernameAndPassword(String username, String password);
}
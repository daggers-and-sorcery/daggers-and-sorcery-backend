package com.morethanheroic.swords.user.repository.domain;

import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDatabaseEntity, String> {

    UserDatabaseEntity findByUsername(String username);

    UserDatabaseEntity findByEmail(String email);

    UserDatabaseEntity findByUsernameAndPassword(String username, String password);
}
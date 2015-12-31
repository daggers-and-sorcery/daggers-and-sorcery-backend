package com.morethanheroic.swords.user.service;

import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class UserManager {

    @Autowired
    private UserMapper userMapper;

    public UserEntity getUser(int id) {
        return new UserEntity(id, userMapper);
    }

    @Transactional
    public UserEntity createUser(String username, String password, String email, Race race) {
        UserDatabaseEntity user = createNewUserEntity(username, password, email, race);

        userMapper.insert(user);

        return getUser(user.getId());
    }

    //TODO: Remove this crap? A facade shouldnt have private stuff lying around.
    private UserDatabaseEntity createNewUserEntity(String username, String password, String email, Race race) {
        UserDatabaseEntity user = new UserDatabaseEntity(username, password);

        final Instant now = Instant.now();

        user.setRegistrationDate(now);
        user.setLastLoginDate(now);
        user.setLastRegenerationDate(now);

        user.setEmail(email);
        user.setRace(race);
        user.setMovement(30);
        user.setHealth(15);
        user.setMana(15);

        return user;
    }
}

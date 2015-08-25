package com.morethanheroic.swords.user.service;

import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserManager {

    private final UserMapper userMapper;

    @Autowired
    public UserManager(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public UserEntity getUser(int id) {
        return new UserEntity(userMapper.findById(id), userMapper);
    }

    public void saveNewUser(String username, String password, String email, Race race) {
        UserDatabaseEntity user = new UserDatabaseEntity(username, password);

        Date date = new Date();

        user.setRegistrationDate(date);
        user.setLastRegenerationDate(date);
        user.setLastLoginDate(date);

        user.setEmail(email);
        user.setRace(race);
        user.setX(0);
        user.setY(0);
        user.setMap(1);
        user.setMovement(30);
        user.setHealth(15);
        user.setMana(15);

        userMapper.insert(user);
    }
}

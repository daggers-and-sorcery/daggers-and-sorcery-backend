package com.morethanheroic.swords.user.domain;

import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Contains the data of an user.
 */
public class UserEntity {

    private final UserDatabaseEntity userDatabaseEntity;
    private final UserMapper userMapper;

    public UserEntity(UserDatabaseEntity userDatabaseEntity, UserMapper userMapper) {
        this.userDatabaseEntity = userDatabaseEntity;
        this.userMapper = userMapper;
    }

    public int getHealthPoints() {
        return userDatabaseEntity.getHealth();
    }

    public int getManaPoints() {
        return userDatabaseEntity.getMana();
    }

    public int getMovementPoints() {
        return userDatabaseEntity.getMovement();
    }

    public void setMovementPoints(int value) {
        userDatabaseEntity.setMovement(value);

        userMapper.updateMovement(userDatabaseEntity.getId(), value);
    }

    public LocalTime getLastRegenerationDate() {
        return userDatabaseEntity.getLastRegenerationDate();
    }

    public int getId() {
        return userDatabaseEntity.getId();
    }

    public String getUsername() {
        return userDatabaseEntity.getUsername();
    }

    public Race getRace() {
        return userDatabaseEntity.getRace();
    }

    public LocalDate getRegistrationDate() {
        return userDatabaseEntity.getRegistrationDate();
    }

    public LocalDate getLastLoginDate() {
        return userDatabaseEntity.getLastLoginDate();
    }

    //TODO: Remove this! This is only here for now! Should refactor it soon! Things that accessed in
    //userDatabaseEntity should be accessed via this class.
    public UserDatabaseEntity getUserDatabaseEntity() {
        return userDatabaseEntity;
    }
}

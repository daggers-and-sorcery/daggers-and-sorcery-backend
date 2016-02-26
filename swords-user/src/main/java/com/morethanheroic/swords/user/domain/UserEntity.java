package com.morethanheroic.swords.user.domain;

import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;

import java.time.Instant;

/**
 * Contains the data of an user.
 */
public class UserEntity extends com.morethanheroic.user.domain.UserEntity {

    private UserDatabaseEntity userDatabaseEntity;
    private UserMapper userMapper;

    public UserEntity(int userId, UserDatabaseEntity userDatabaseEntity, UserMapper userMapper) {
        super(userId, userDatabaseEntity.getUsername(), userDatabaseEntity.getPassword(), userDatabaseEntity.getEmail());

        this.userDatabaseEntity = userDatabaseEntity;
        this.userMapper = userMapper;
    }

    public UserEntity(int id, String username, String password, String email) {
        super(id, username, password, email);
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

    public Instant getLastRegenerationDate() {
        return userDatabaseEntity.getLastRegenerationDate();
    }

    public Race getRace() {
        return userDatabaseEntity.getRace();
    }

    public Instant getRegistrationDate() {
        return userDatabaseEntity.getRegistrationDate();
    }

    public Instant getLastLoginDate() {
        return userDatabaseEntity.getLastLoginDate();
    }

    //TODO: Remove this! This is only here for now! Should refactor it soon! Things that accessed in
    //userDatabaseEntity should be accessed via this class.
    public UserDatabaseEntity getUserDatabaseEntity() {
        return userDatabaseEntity;
    }
}

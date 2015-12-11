package com.morethanheroic.swords.user.domain;

import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.regeneration.domain.RegenerationEntity;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;

import java.util.Date;

public class UserEntity {

    private final UserDatabaseEntity userDatabaseEntity;
    private final UserMapper userMapper;

    private RegenerationEntity regenerationEntity;

    public UserEntity(UserDatabaseEntity userDatabaseEntity, UserMapper userMapper) {
        this.userDatabaseEntity = userDatabaseEntity;
        this.userMapper = userMapper;
    }

    public RegenerationEntity getRegeneration() {
        if (regenerationEntity == null) {
            regenerationEntity = new RegenerationEntity(userDatabaseEntity, userMapper);
        }

        return regenerationEntity;
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

    public Date getRegistrationDate() {
        return userDatabaseEntity.getRegistrationDate();
    }

    public Date getLastLoginDate() {
        return userDatabaseEntity.getLastLoginDate();
    }

    //TODO: Remove this! This is only here for now! Should refactor it soon! Things that accessed in userDatabaseEntity should be accessed via this class.
    public UserDatabaseEntity getUserDatabaseEntity() {
        return userDatabaseEntity;
    }
}

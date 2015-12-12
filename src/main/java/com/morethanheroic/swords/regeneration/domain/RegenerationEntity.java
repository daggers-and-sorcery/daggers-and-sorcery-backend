package com.morethanheroic.swords.regeneration.domain;

import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;

import java.util.Date;

public class RegenerationEntity {

    private final UserDatabaseEntity userDatabaseEntity;
    private final UserMapper userMapper;

    public RegenerationEntity(UserDatabaseEntity userDatabaseEntity, UserMapper userMapper) {
        this.userDatabaseEntity = userDatabaseEntity;
        this.userMapper = userMapper;
    }

    public void regenerate(int health, int mana, int movement, Date date) {
        userDatabaseEntity.setLastRegenerationDate(date);
        userDatabaseEntity.setMana(mana);
        userDatabaseEntity.setHealth(health);
        userDatabaseEntity.setMovement(movement);

        userMapper.updateRegeneration(userDatabaseEntity.getId(), health, mana, movement, date);
    }
}

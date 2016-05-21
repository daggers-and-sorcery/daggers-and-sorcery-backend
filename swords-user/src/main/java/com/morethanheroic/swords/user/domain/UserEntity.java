package com.morethanheroic.swords.user.domain;

import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;

import java.time.Instant;

/**
 * Contains the data of an user. These methods doesn't take the attribute modifications and maximum/minimum values
 * into account, this class only represent the raw user data in the database. If you need the maximum/minimum values or
 * want to change these attributes use the classes that contains the logic for the changings and don't use this class
 * directly!
 */
public class UserEntity {

    private final UserDatabaseEntity userDatabaseEntity;
    private final UserMapper userMapper;

    public UserEntity(int userId, UserMapper userMapper) {
        this.userDatabaseEntity = userMapper.findById(userId);
        this.userMapper = userMapper;
    }

    public int getHealthPoints() {
        return userDatabaseEntity.getHealth();
    }

    public void setHealthPoints(final int value) {
        userDatabaseEntity.setHealth(value);

        userMapper.updateHealth(userDatabaseEntity.getId(), value);
    }

    public int getManaPoints() {
        return userDatabaseEntity.getMana();
    }

    public void setManaPoints(final int value) {
        userDatabaseEntity.setMana(value);

        userMapper.updateMana(userDatabaseEntity.getId(), value);
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

    public int getId() {
        return userDatabaseEntity.getId();
    }

    public String getUsername() {
        return userDatabaseEntity.getUsername();
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

    public void setBasicStats(int health, int mana, int movement) {
        userDatabaseEntity.setHealth(health);
        userDatabaseEntity.setMana(mana);
        userDatabaseEntity.setMovement(movement);

        userMapper.updateBasicCombatStats(userDatabaseEntity.getId(), health, mana, movement);
    }

    public int getActiveExplorationEvent() {
        return userDatabaseEntity.getExplorationEvent();
    }

    public int getActiveExplorationState() {
        return userDatabaseEntity.getExplorationState();
    }

    public void resetActiveExploration() {
        setActiveExploration(0, 0);
    }

    public void setActiveExploration(int event, int state) {
        userDatabaseEntity.setExplorationEvent(event);
        userDatabaseEntity.setExplorationState(state);

        userMapper.updateExploration(event, state, userDatabaseEntity.getId());
    }

    public void updateLastRegenerationDate(Instant date) {
        userDatabaseEntity.setLastRegenerationDate(date);

        userMapper.updateRegenerationDate(userDatabaseEntity.getId(), date);
    }
}

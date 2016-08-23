package com.morethanheroic.swords.user.domain;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.morethanheroic.entity.domain.Entity;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;

/**
 * Contains the data of an user. These methods doesn't take the attribute modifications and maximum/minimum values
 * into account, this class only represent the raw user data in the database. If you need the maximum/minimum values or
 * want to change these attributes use the classes that contains the logic for the changings and don't use this class
 * directly!
 */
@Configurable
public class UserEntity implements Entity {

    @Autowired
    private UserMapper userMapper;

    private final UserDatabaseEntity userDatabaseEntity;

    public UserEntity(final UserDatabaseEntity userDatabaseEntity) {
        this.userDatabaseEntity = userDatabaseEntity;
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

    @Override
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

    public void setLastLoginDateToNow() {
        userMapper.updateLastLoginDate(getId());
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

    @Override
    public String toString() {
        return "UserEntity(id=" + getId() + ", username=" + getUsername() + ")";
    }
}

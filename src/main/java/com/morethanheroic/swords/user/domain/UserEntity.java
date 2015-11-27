package com.morethanheroic.swords.user.domain;

import com.morethanheroic.swords.common.container.ServiceContainer;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.movement.domain.MovementEntity;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;

import java.util.Date;

public class UserEntity {

    private final UserDatabaseEntity userDatabaseEntity;
    private final ServiceContainer serviceContainer;
    private final UserMapper userMapper;

    private InventoryEntity inventoryEntity;
    private MovementEntity movementEntity;

    public UserEntity(UserDatabaseEntity userDatabaseEntity, ServiceContainer serviceContainer, UserMapper userMapper) {
        this.userDatabaseEntity = userDatabaseEntity;
        this.serviceContainer = serviceContainer;
        this.userMapper = userMapper;
    }

    public InventoryEntity getInventory() {
        if (inventoryEntity == null) {
            inventoryEntity = serviceContainer.getInventoryFacade().getInventory(this);
        }

        return inventoryEntity;
    }

    public MovementEntity getMovement() {
        if(movementEntity == null) {
            movementEntity = new MovementEntity(userDatabaseEntity, userMapper, serviceContainer.getMapManager());
        }

        return movementEntity;
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

    public Date getLastRegenerationDate() {
        return userDatabaseEntity.getLastRegenerationDate();
    }

    public int getScavengingPoint() {
        return userDatabaseEntity.getScaveningPoint();
    }

    public void setScavengingPoint(int value) {
        userMapper.updateScavengingPoint(userDatabaseEntity.getId(), value);
    }

    public void regenerate(int health, int mana, int movement, Date date) {
        userDatabaseEntity.setLastRegenerationDate(date);
        userDatabaseEntity.setMana(mana);
        userDatabaseEntity.setHealth(health);
        userDatabaseEntity.setMovement(movement);

        userMapper.updateRegeneration(userDatabaseEntity.getId(), health, mana, movement, date);
    }
}

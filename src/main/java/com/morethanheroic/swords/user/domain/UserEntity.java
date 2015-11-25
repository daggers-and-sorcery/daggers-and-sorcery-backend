package com.morethanheroic.swords.user.domain;

import com.morethanheroic.swords.common.container.ServiceContainer;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.map.service.domain.MapInfoDefinition;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;

import java.util.Date;

public class UserEntity {

    private final UserDatabaseEntity userDatabaseEntity;
    private final ServiceContainer serviceContainer;
    private final UserMapper userMapper;

    private InventoryEntity inventoryEntity;

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

    public int getMapId() {
        return userDatabaseEntity.getMap();
    }

    public MapInfoDefinition getMap() {
        return serviceContainer.getMapInfoDefinitionManager().getMapInfoDefinition(userDatabaseEntity.getMap());
    }

    public void setPosition(int x, int y) {
        userDatabaseEntity.setX(x);
        userDatabaseEntity.setY(y);

        userMapper.updatePosition(userDatabaseEntity.getId(), x, y);
    }

    public void setMap(int mapId) {
        userDatabaseEntity.setMap(mapId);
    }

    public int getX() {
        return userDatabaseEntity.getX();
    }

    public int getY() {
        return userDatabaseEntity.getY();
    }

    public int getHealth() {
        return userDatabaseEntity.getHealth();
    }

    public int getMana() {
        return userDatabaseEntity.getMana();
    }

    public int getMovement() {
        return userDatabaseEntity.getMovement();
    }

    public void setMovement(int value) {
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

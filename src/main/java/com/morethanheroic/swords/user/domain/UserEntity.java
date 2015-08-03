package com.morethanheroic.swords.user.domain;

import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.skill.service.Skills;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.map.domain.MapEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntity;

import java.util.Date;

public class UserEntity {

    private final UserDatabaseEntity userDatabaseEntity;
    private final InventoryEntity inventoryEntity;
    private final MapManager mapManager;

    public UserEntity(UserDatabaseEntity userDatabaseEntity) {
        this(userDatabaseEntity, null);
    }

    public UserEntity(UserDatabaseEntity userDatabaseEntity, MapManager mapManager) {
        this.userDatabaseEntity = userDatabaseEntity;
        this.inventoryEntity = new InventoryEntity(userDatabaseEntity);
        this.mapManager = mapManager;
    }

    public int getId() {
        return userDatabaseEntity.getId();
    }

    public String getUsername() {
        return userDatabaseEntity.getUsername();
    }

    public InventoryEntity getInventory() {
        return inventoryEntity;
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

    public Skills getSkills() {
        return userDatabaseEntity.getSkills();
    }

    public UserDatabaseEntity getUserDatabaseEntity() {
        return userDatabaseEntity;
    }

    public MapEntity getMap() {
        return mapManager.getMap(userDatabaseEntity.getMap());
    }

    public void setPosition(int x, int y) {
        userDatabaseEntity.setX(x);
        userDatabaseEntity.setY(y);
    }

    public int getXPosition() {
        return userDatabaseEntity.getX();
    }

    public int getYPosition() {
        return userDatabaseEntity.getY();
    }
}

package com.swordssorcery.server.model.entity.user;

import com.swordssorcery.server.game.map.MapManager;
import com.swordssorcery.server.game.race.Race;
import com.swordssorcery.server.model.db.Skills;
import com.swordssorcery.server.model.db.user.UserDatabaseEntity;
import com.swordssorcery.server.model.entity.map.MapEntity;

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
        return mapManager.getMap(userDatabaseEntity.getPosition().getMap());
    }

    public void setPosition(int x, int y) {
        userDatabaseEntity.getPosition().setPosition(x, y);
    }

    public int getXPosition() {
        return userDatabaseEntity.getPosition().getX();
    }

    public int getYPosition() {
        return userDatabaseEntity.getPosition().getY();
    }
}

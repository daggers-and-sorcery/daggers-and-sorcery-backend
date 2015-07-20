package com.swordssorcery.server.model.entity.user;

import com.swordssorcery.server.game.race.Race;
import com.swordssorcery.server.model.db.Inventory;
import com.swordssorcery.server.model.db.Skills;
import com.swordssorcery.server.model.db.user.UserDatabaseEntity;

import java.util.Date;

public class UserEntity {

    private final UserDatabaseEntity userDatabaseEntity;

    public UserEntity(UserDatabaseEntity userDatabaseEntity) {
        this.userDatabaseEntity = userDatabaseEntity;
    }

    public String getUsername() {
        return userDatabaseEntity.getUsername();
    }

    public Inventory getInventory() {
        return userDatabaseEntity.getInventory();
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
}

package com.morethanheroic.swords.user.domain;

import com.morethanheroic.swords.attribute.domain.AttributeEntity;
import com.morethanheroic.swords.common.container.ServiceContainer;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.movement.domain.MovementEntity;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.regeneration.domain.RegenerationEntity;
import com.morethanheroic.swords.settings.model.SettingsEntity;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;

import java.util.Date;

public class UserEntity {

    private final UserDatabaseEntity userDatabaseEntity;
    private final ServiceContainer serviceContainer;
    private final UserMapper userMapper;

    private InventoryEntity inventoryEntity;
    private MovementEntity movementEntity;
    private RegenerationEntity regenerationEntity;
    private SkillEntity skillEntity;
    private AttributeEntity attributeEntity;
    private SettingsEntity settingsEntity;

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

    public RegenerationEntity getRegeneration() {
        if(regenerationEntity == null) {
            regenerationEntity = new RegenerationEntity(userDatabaseEntity, userMapper);
        }

        return regenerationEntity;
    }

    public SkillEntity getSkills() {
        if(skillEntity == null) {
            skillEntity = serviceContainer.getSkillManager().getSkills(this);
        }

        return skillEntity;
    }

    public AttributeEntity getAttributes() {
        if(attributeEntity == null) {
            this.attributeEntity = new AttributeEntity(this, serviceContainer.getGlobalAttributeCalculator());
        }

        return attributeEntity;
    }

    public SettingsEntity getSettings() {
        if(settingsEntity == null) {
            settingsEntity = serviceContainer.getSettingsManager().getSettings(this);
        }

        return settingsEntity;
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
}

package com.morethanheroic.swords.scavenging.service;

import com.morethanheroic.swords.scavenging.domain.ScavengingResult;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.scavenging.domain.ScavengingEntity;
import com.morethanheroic.swords.settings.model.SettingsEntity;
import com.morethanheroic.swords.settings.service.SettingsFacade;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.SkillFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ScavengingFacade {

    @Autowired
    private ScavengingAwarder scavengingAwarder;

    @Autowired
    private ScavengingCalculator scavengingCalculator;

    @Autowired
    private InventoryFacade inventoryFacade;

    @Autowired
    private SkillFacade skillFacade;

    @Autowired
    private SettingsFacade settingsFacade;

    @Autowired
    //TODO: we don't want to tie ourselves to the UserMapper! This should be removed asap!
    private UserMapper userMapper;

    public ScavengingEntity getEntity(UserEntity userEntity) {
        return new ScavengingEntity(userEntity, userMapper);
    }

    public ScavengingResult handleScavenging(UserEntity userEntity, MonsterDefinition monsterDefinition) {
        SettingsEntity settingsEntity = settingsFacade.getSettings(userEntity);
        ScavengingEntity scavengingEntity = getEntity(userEntity);
        SkillEntity skillEntity = skillFacade.getSkills(userEntity);
        InventoryEntity inventoryEntity = inventoryFacade.getInventory(userEntity);

        if (shouldScavenge(settingsEntity, scavengingEntity)) {
            ScavengingResult scavengingResult = scavengingCalculator.calculateScavenge(skillEntity, monsterDefinition);

            scavengingAwarder.awardScavengingResultToUser(skillEntity, inventoryEntity, scavengingResult);

            decreaseUserScavengingPoints(scavengingEntity);

            return scavengingResult;
        }

        return new ScavengingResult(Collections.emptyList(), 0);
    }

    private void decreaseUserScavengingPoints(ScavengingEntity scavengingEntity) {
        scavengingEntity.setScavengingPoint(scavengingEntity.getScavengingPoint() - 1);
    }

    private boolean shouldScavenge(SettingsEntity settingsEntity, ScavengingEntity scavengingEntity) {
        return settingsEntity.isScavengingEnabled() && scavengingEntity.getScavengingPoint() > 0;
    }
}

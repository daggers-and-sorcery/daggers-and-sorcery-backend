package com.morethanheroic.swords.skill.service;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.adder.ScavengingAwarder;
import com.morethanheroic.swords.combat.service.calc.scavenge.ScavengingCalculator;
import com.morethanheroic.swords.combat.service.calc.scavenge.domain.ScavengingResult;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.settings.model.SettingsEntity;
import com.morethanheroic.swords.settings.service.SettingsManager;
import com.morethanheroic.swords.skill.domain.ScavengingEntity;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScavengingFacade {

    @Autowired
    private ScavengingAwarder scavengingAwarder;

    @Autowired
    private ScavengingCalculator scavengingCalculator;

    @Autowired
    private InventoryFacade inventoryFacade;

    @Autowired
    private SkillManager skillManager;

    @Autowired
    private SettingsManager settingsManager;

    @Autowired
    private UserMapper userMapper;

    public ScavengingEntity getEntity(UserEntity userEntity) {
        return new ScavengingEntity(userEntity, userMapper);
    }

    public void handleScavenging(CombatResult combatResult, UserEntity userEntity, MonsterDefinition monsterDefinition) {
        SettingsEntity settingsEntity = settingsManager.getSettings(userEntity);
        ScavengingEntity scavengingEntity = getEntity(userEntity);
        SkillEntity skillEntity = skillManager.getSkills(userEntity);
        InventoryEntity inventoryEntity = inventoryFacade.getInventory(userEntity);

        if (shouldScavenge(settingsEntity, scavengingEntity)) {
            ScavengingResult scavengingResult = scavengingCalculator.calculateScavenge(skillEntity, monsterDefinition);

            scavengingAwarder.awardScavengingResultToUser(combatResult, skillEntity, inventoryEntity, scavengingResult);

            decreaseUserScavengingPoints(scavengingEntity);
        }
    }

    private void decreaseUserScavengingPoints(ScavengingEntity scavengingEntity) {
        scavengingEntity.setScavengingPoint(scavengingEntity.getScavengingPoint() - 1);
    }

    private boolean shouldScavenge(SettingsEntity settingsEntity, ScavengingEntity scavengingEntity) {
        return settingsEntity.isScavengingEnabled() && scavengingEntity.getScavengingPoint() > 0;
    }
}

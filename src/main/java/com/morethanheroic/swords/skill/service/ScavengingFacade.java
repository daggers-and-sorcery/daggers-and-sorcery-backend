package com.morethanheroic.swords.skill.service;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.adder.ScavengingAwarder;
import com.morethanheroic.swords.combat.service.calc.scavenge.ScavengingCalculator;
import com.morethanheroic.swords.combat.service.calc.scavenge.domain.ScavengingResult;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.settings.model.SettingsEntity;
import com.morethanheroic.swords.skill.domain.ScavengingEntity;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScavengingFacade {

    @Autowired
    private ScavengingAwarder scavengingAwarder;

    @Autowired
    private ScavengingCalculator scavengingCalculator;

    public void handleScavenging(CombatResult combatResult, UserEntity userEntity, MonsterDefinition monsterDefinition) {
        SettingsEntity settingsEntity = userEntity.getSettings();
        ScavengingEntity scavengingEntity = userEntity.getSkills().getScavenging();
        SkillEntity skillEntity = userEntity.getSkills();
        InventoryEntity inventoryEntity = userEntity.getInventory();

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

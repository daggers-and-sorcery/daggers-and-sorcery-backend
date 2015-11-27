package com.morethanheroic.swords.combat.service.adder;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.calc.scavenge.ScavengingCalculator;
import com.morethanheroic.swords.combat.service.calc.scavenge.domain.ScavengingResult;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.settings.model.SettingsEntity;
import com.morethanheroic.swords.skill.domain.ScavengingEntity;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.scavenge.ScavengeDropAwarder;
import com.morethanheroic.swords.skill.service.scavenge.ScavengeExperienceAwarder;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScavengingAdder {

    @Autowired
    private ScavengingCalculator scavengingCalculator;

    @Autowired
    private ScavengeDropAwarder scavengeDropAwarder;

    @Autowired
    private ScavengeExperienceAwarder scavengeExperienceAwarder;

    public void addScavengingResultsToUserFromMonsterDefinition(CombatResult result, UserEntity user, MonsterDefinition monsterDefinition) {
        SettingsEntity settingsEntity = user.getSettings();
        ScavengingEntity scavengingEntity = user.getSkills().getScavenging();
        SkillEntity skillEntity = user.getSkills();
        InventoryEntity inventoryEntity = user.getInventory();

        if (shouldScavenge(settingsEntity, scavengingEntity)) {
            ScavengingResult scavengingResult =  scavengingCalculator.calculateScavenge(skillEntity, monsterDefinition);

            scavengeDropAwarder.awardScavengingDrops(result, inventoryEntity, scavengingResult.getScavengingResultList());
            scavengeExperienceAwarder.awardScavengingXp(skillEntity, monsterDefinition, scavengingResult.isSuccessfullScavenge());

            decreaseUserScavengingPoints(scavengingEntity);
        }
    }

    private boolean shouldScavenge(SettingsEntity settingsEntity, ScavengingEntity scavengingEntity) {
        return settingsEntity.isScavengingEnabled() && scavengingEntity.getScavengingPoint() > 0;
    }

    private void decreaseUserScavengingPoints(ScavengingEntity scavengingEntity) {
        scavengingEntity.setScavengingPoint(scavengingEntity.getScavengingPoint() - 1);
    }
}

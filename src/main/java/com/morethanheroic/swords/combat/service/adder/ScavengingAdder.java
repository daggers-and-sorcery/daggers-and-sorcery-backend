package com.morethanheroic.swords.combat.service.adder;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.ScavengingEntity;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.calc.scavenge.ScavengingCalculator;
import com.morethanheroic.swords.combat.service.calc.scavenge.domain.ScavengingResult;
import com.morethanheroic.swords.settings.repository.domain.SettingsMapper;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.skill.service.SkillManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScavengingAdder {

    @Autowired
    private SettingsMapper settingsMapper;

    @Autowired
    private ScavengingCalculator scavengingCalculator;

    @Autowired
    private CombatMessageBuilder combatMessageBuilder;

    @Autowired
    private InventoryFacade inventoryFacade;

    @Autowired
    private SkillManager skillManager;

    public void addScavengingResultsToUserFromMonsterDefinition(CombatResult result, UserEntity user, MonsterDefinition monster) {
        if (shouldScavenge(user)) {
            ScavengingResult scavengingResult =  scavengingCalculator.calculateScavenge(user, monster);

            awardScavengingDrops(result, user, scavengingResult.getScavengingResultList());
            awardScavengingXp(user, monster, scavengingResult.isSuccessfullScavenge());

            decreaseUserScavengingPoints(user);
        }
    }

    public boolean shouldScavenge(UserEntity user) {
        return settingsMapper.getSettings(user.getId()).isScavengingEnabled() && user.getSkills().getScavenging().getScavengingPoint() > 0;
    }

    private void awardScavengingDrops(CombatResult combatResult, UserEntity user, List<ScavengingEntity> scavengingResultList) {
        InventoryEntity inventory = inventoryFacade.getInventory(user);

        for (ScavengingEntity scavengingEntity : scavengingResultList) {
            if(scavengingEntity.isIdentified()) {
                combatResult.addMessage(combatMessageBuilder.buildScavengeMessage(scavengingEntity.getItem().getName(), scavengingEntity.getAmount()));
            } else {
                combatResult.addMessage(combatMessageBuilder.buildScavengeMessage("Unidentified item", scavengingEntity.getAmount()));
            }

            inventory.addItem(scavengingEntity.getItem(), scavengingEntity.getAmount(), scavengingEntity.isIdentified());
        }
    }

    private void awardScavengingXp(UserEntity user, MonsterDefinition monster, boolean successfulScavenging) {
        skillManager.getSkills(user).addSkillXp(SkillAttribute.SCAVENGING, calculateScavengingXp(monster, successfulScavenging));
    }

    private int calculateScavengingXp(MonsterDefinition monster, boolean successfulScavenging) {
        if (successfulScavenging) {
            return monster.getLevel() * 5;
        } else {
            return monster.getLevel();
        }
    }

    //TODO: wtf?
    private void decreaseUserScavengingPoints(UserEntity user) {
        user.getSkills().getScavenging().setScavengingPoint(user.getSkills().getScavenging().getScavengingPoint() - 1);
    }
}

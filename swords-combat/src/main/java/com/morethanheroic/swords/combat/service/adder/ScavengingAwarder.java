package com.morethanheroic.swords.combat.service.adder;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.ScavengingResultEntity;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.calc.scavenge.domain.ScavengingResult;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScavengingAwarder {

    private static final String UNIDENTIFIED_ITEM_NAME = "Unidentified item";

    @Autowired
    private CombatMessageBuilder combatMessageBuilder;

    public void awardScavengingResultToUser(CombatResult combatResult, SkillEntity skillEntity, InventoryEntity inventoryEntity, ScavengingResult scavengingResult) {
        awardScavengingDrops(combatResult, inventoryEntity, scavengingResult);
        awardScavengingXp(combatResult, skillEntity, scavengingResult);
    }

    private void awardScavengingDrops(CombatResult combatResult, InventoryEntity inventoryEntity, ScavengingResult scavengingResult) {
        for (ScavengingResultEntity scavengingResultEntity : scavengingResult.getScavengingResultList()) {
            if (scavengingResultEntity.isIdentified()) {
                combatResult.addMessage(combatMessageBuilder.buildScavengeItemAwardMessage(scavengingResultEntity.getItem().getName(), scavengingResultEntity.getAmount()));
            } else {
                combatResult.addMessage(combatMessageBuilder.buildScavengeItemAwardMessage(UNIDENTIFIED_ITEM_NAME, scavengingResultEntity.getAmount()));
            }

            inventoryEntity.addItem(scavengingResultEntity.getItem(), scavengingResultEntity.getAmount(), scavengingResultEntity.isIdentified());
        }
    }

    private void awardScavengingXp(CombatResult combatResult, SkillEntity skillEntity, ScavengingResult scavengingResult) {
        combatResult.addMessage(combatMessageBuilder.buildScavengeXpAwardMessage(scavengingResult.getScavengingXp()));

        skillEntity.addSkillXp(SkillType.SCAVENGING, scavengingResult.getScavengingXp());
    }
}

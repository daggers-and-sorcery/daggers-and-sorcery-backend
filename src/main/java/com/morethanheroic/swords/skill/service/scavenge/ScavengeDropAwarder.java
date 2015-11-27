package com.morethanheroic.swords.skill.service.scavenge;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.ScavengingResultEntity;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScavengeDropAwarder {

    @Autowired
    private CombatMessageBuilder combatMessageBuilder;

    public void awardScavengingDrops(CombatResult combatResult, InventoryEntity inventoryEntity, List<ScavengingResultEntity> scavengingResultList) {
        for (ScavengingResultEntity scavengingResultEntity : scavengingResultList) {
            if(scavengingResultEntity.isIdentified()) {
                combatResult.addMessage(combatMessageBuilder.buildScavengeMessage(scavengingResultEntity.getItem().getName(), scavengingResultEntity.getAmount()));
            } else {
                combatResult.addMessage(combatMessageBuilder.buildScavengeMessage("Unidentified item", scavengingResultEntity.getAmount()));
            }

            inventoryEntity.addItem(scavengingResultEntity.getItem(), scavengingResultEntity.getAmount(), scavengingResultEntity.isIdentified());
        }
    }
}

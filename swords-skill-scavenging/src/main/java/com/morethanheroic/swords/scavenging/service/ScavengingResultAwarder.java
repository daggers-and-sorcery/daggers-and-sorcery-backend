package com.morethanheroic.swords.scavenging.service;

import com.morethanheroic.swords.scavenging.domain.ScavengingResultEntity;
import com.morethanheroic.swords.scavenging.domain.ScavengingResult;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import org.springframework.stereotype.Service;

@Service
public class ScavengingResultAwarder {

    public void awardScavengingResultToUser(SkillEntity skillEntity, InventoryEntity inventoryEntity, ScavengingResult scavengingResult) {
        awardScavengingDrops(inventoryEntity, scavengingResult);
        awardScavengingXp(skillEntity, scavengingResult);
    }

    private void awardScavengingDrops(InventoryEntity inventoryEntity, ScavengingResult scavengingResult) {
        for (ScavengingResultEntity scavengingResultEntity : scavengingResult.getScavengingResultList()) {
            inventoryEntity.addItem(scavengingResultEntity.getItem(), scavengingResultEntity.getAmount(), scavengingResultEntity.isIdentified());
        }
    }

    private void awardScavengingXp(SkillEntity skillEntity, ScavengingResult scavengingResult) {
        skillEntity.increaseExperience(SkillType.SCAVENGING, scavengingResult.getScavengingXp());
    }
}

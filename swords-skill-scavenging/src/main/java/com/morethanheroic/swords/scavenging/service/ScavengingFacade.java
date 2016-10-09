package com.morethanheroic.swords.scavenging.service;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.scavenging.domain.ScavengingEntity;
import com.morethanheroic.swords.scavenging.domain.ScavengingResult;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.SkillFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ScavengingFacade {

    @Autowired
    private ScavengingResultAwarder scavengingResultAwarder;

    @Autowired
    private ScavengingCalculator scavengingCalculator;

    @Autowired
    private InventoryFacade inventoryFacade;

    @Autowired
    private SkillFacade skillFacade;

    @Autowired
    private ScavengingEntityFactory scavengingEntityFactory;

    public ScavengingResult handleScavenging(UserEntity userEntity, MonsterDefinition monsterDefinition) {
        ScavengingEntity scavengingEntity = scavengingEntityFactory.getEntity(userEntity.getId());
        SkillEntity skillEntity = skillFacade.getSkills(userEntity);
        InventoryEntity inventoryEntity = inventoryFacade.getInventory(userEntity);

        if (shouldScavenge(scavengingEntity)) {
            ScavengingResult scavengingResult = scavengingCalculator.calculateScavenge(skillEntity, monsterDefinition);

            scavengingResultAwarder.awardScavengingResultToUser(skillEntity, inventoryEntity, scavengingResult);

            decreaseUserScavengingPoints(scavengingEntity);

            return scavengingResult;
        }

        return new ScavengingResult(Collections.emptyList(), 0);
    }

    private void decreaseUserScavengingPoints(final ScavengingEntity scavengingEntity) {
        scavengingEntity.setScavengingPoint(scavengingEntity.getScavengingPoint() - 1);
    }

    private boolean shouldScavenge(final ScavengingEntity scavengingEntity) {
        return scavengingEntity.isScavengingEnabled() && scavengingEntity.getScavengingPoint() > 0;
    }
}

package com.morethanheroic.swords.scavenging.service;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.scavenging.domain.ScavengingEntity;
import com.morethanheroic.swords.scavenging.domain.ScavengingResult;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class ScavengingAttemptCalculator {

    private final ScavengingResultAwarder scavengingResultAwarder;
    private final ScavengingCalculator scavengingCalculator;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final SkillEntityFactory skillEntityFactory;
    private final ScavengingEntityFactory scavengingEntityFactory;

    public ScavengingResult handleScavenging(UserEntity userEntity, MonsterDefinition monsterDefinition) {
        final ScavengingEntity scavengingEntity = scavengingEntityFactory.getEntity(userEntity);
        final SkillEntity skillEntity = skillEntityFactory.getEntity(userEntity.getId());
        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity.getId());

        if (shouldScavenge(scavengingEntity)) {
            final ScavengingResult scavengingResult = scavengingCalculator.calculateScavenge(userEntity, skillEntity, monsterDefinition);

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

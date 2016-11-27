package com.morethanheroic.swords.scavenging.service;

import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.monster.domain.ScavengingAmountDefinition;
import com.morethanheroic.swords.scavenging.domain.ScavengingResult;
import com.morethanheroic.swords.scavenging.domain.ScavengingResultEntity;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScavengingCalculator {

    private final ScavengingChanceCalculator scavengingChanceCalculator;
    private final Random random;

    public ScavengingResult calculateScavenge(final UserEntity userEntity, final SkillEntity skillEntity, final MonsterDefinition monster) {
        final List<ScavengingResultEntity> result = new ArrayList<>();

        final int scavengingLevel = skillEntity.getLevel(SkillType.SCAVENGING);

        result.addAll(
                monster.getScavengingDefinitions().stream()
                        .filter(scavengingDefinition -> 100 * random.nextDouble() < scavengingChanceCalculator.calculateScavengingChance(userEntity, scavengingDefinition.getChance(), monster.getLevel(), scavengingLevel))
                        .map(scavengingDefinition -> new ScavengingResultEntity(scavengingDefinition.getItem(), calculateScavengingAmount(scavengingDefinition.getAmount()), scavengingDefinition.isIdentified()))
                        .collect(Collectors.toList())
        );

        final int scavengingXp = calculateScavengingXp(monster, isSuccessfulScavenge(result));

        return new ScavengingResult(result, scavengingXp);
    }

    private int calculateScavengingAmount(ScavengingAmountDefinition scavengingAmountDefinition) {
        if (scavengingAmountDefinition.getMinimumAmount() == scavengingAmountDefinition.getMaximumAmount()) {
            return scavengingAmountDefinition.getMinimumAmount();
        }

        return random.nextInt(scavengingAmountDefinition.getMaximumAmount() - scavengingAmountDefinition.getMinimumAmount()) + scavengingAmountDefinition.getMinimumAmount();
    }

    private boolean isSuccessfulScavenge(List<ScavengingResultEntity> result) {
        return result.size() > 0;
    }

    private int calculateScavengingXp(MonsterDefinition monsterDefinition, boolean successfulScavenging) {
        if (successfulScavenging) {
            return monsterDefinition.getLevel() * 5;
        } else {
            return monsterDefinition.getLevel();
        }
    }
}

package com.morethanheroic.swords.combat.service.calc.scavenge;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.combat.domain.ScavengingResultEntity;
import com.morethanheroic.swords.combat.service.calc.scavenge.domain.ScavengingResult;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.monster.domain.ScavengingAmountDefinition;
import com.morethanheroic.swords.monster.domain.ScavengingDefinition;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.SkillManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class ScavengingCalculator {

    @Autowired
    private Random random;

    public ScavengingResult calculateScavenge(SkillEntity skillEntity, MonsterDefinition monster) {
        ArrayList<ScavengingResultEntity> result = new ArrayList<>();

        int scavengingLevel = skillEntity.getSkillLevel(SkillAttribute.SCAVENGING);

        for (ScavengingDefinition scavengingDefinition : monster.getScavengingDefinitions()) {
            if (100 * random.nextDouble() < calculateScavengingChance(scavengingDefinition.getChance(), monster.getLevel(), scavengingLevel)) {
                result.add(new ScavengingResultEntity(scavengingDefinition.getItem(), calculateScavengingAmount(scavengingDefinition.getAmount()), scavengingDefinition.isIdentified()));
            }
        }

        return new ScavengingResult(result);
    }

    /**
     * Loot chance is calculated with this formula: drop_chance - (abs(monster_level - scavenging_level) / 10)
     *
     * For example a lvl 10 monster has 3% chance to drop an item while scavenging, and the characters scavenging level is 5:
     * 3 - (abs(10 - 5) / 10) = 2.5
     * The chance for the item to drop is 2.5%.
     */
    private double calculateScavengingChance(double dropChance, int monsterLevel, int scavengingLevel) {
        return dropChance - (Math.abs(monsterLevel - scavengingLevel) / 10);
    }

    private int calculateScavengingAmount(ScavengingAmountDefinition scavengingAmountDefinition) {
        if(scavengingAmountDefinition.getMinimumAmount() == scavengingAmountDefinition.getMaximumAmount()) {
            return scavengingAmountDefinition.getMinimumAmount();
        }

        return random.nextInt(scavengingAmountDefinition.getMaximumAmount() - scavengingAmountDefinition.getMinimumAmount()) + scavengingAmountDefinition.getMinimumAmount();
    }
}

package com.morethanheroic.swords.scavenging.service;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScavengingChanceCalculator {

    private final GlobalAttributeCalculator globalAttributeCalculator;

    /**
     * Loot chance is calculated with this formula: drop_chance - (abs(monster_level - scavenging_level) / 10)
     * <p>
     * For example a lvl 10 monster has 3% chance to drop an item while scavenging, and the characters scavenging level is 5:
     * 3 - (abs(10 - 5) / 10) = 2.5
     * The chance for the item to drop is 2.5%.
     */
    public double calculateScavengingChance(final UserEntity userEntity, double dropChance, int monsterLevel, int scavengingLevel) {
        final double scavengingBonus = globalAttributeCalculator.calculateActualValue(userEntity, BasicAttribute.SCAVENGING_BONUS).getValue();

        return (dropChance - (Math.abs(monsterLevel - scavengingLevel) / 10)) * (1D + scavengingBonus / 100D);
    }
}

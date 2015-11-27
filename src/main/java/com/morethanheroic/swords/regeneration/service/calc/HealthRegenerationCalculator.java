package com.morethanheroic.swords.regeneration.service.calc;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthRegenerationCalculator implements RegenerationCalculator {

    private static final int HEALTH_REGENERATION_UNIT = 2;

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Override
    public int calculateRegeneration(UserEntity user, int durationToCalculate) {
        int newHealth = user.getRegeneration().getHealthPoints() + HEALTH_REGENERATION_UNIT * durationToCalculate;
        int maxHealth = globalAttributeCalculator.calculateMaximumValue(user, CombatAttribute.LIFE).getValue();

        if (newHealth > maxHealth) {
            return maxHealth;
        }

        return newHealth;
    }
}

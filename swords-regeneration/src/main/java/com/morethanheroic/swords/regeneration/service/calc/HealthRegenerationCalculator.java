package com.morethanheroic.swords.regeneration.service.calc;

import org.springframework.stereotype.Service;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HealthRegenerationCalculator implements RegenerationCalculator {

    private final GlobalAttributeCalculator globalAttributeCalculator;

    @Override
    public int calculateRegeneration(UserEntity userEntity, int durationToCalculate) {
        int newHealth = calculateNewHealth(userEntity, durationToCalculate);
        int maxHealth = calculateMaxHealth(userEntity);

        return newHealth > maxHealth ? maxHealth : newHealth;
    }

    private int calculateNewHealth(final UserEntity userEntity, final int durationToCalculate) {
        return userEntity.getHealthPoints() + calculateHealthRegeneration(userEntity) * durationToCalculate;
    }

    private int calculateHealthRegeneration(final UserEntity userEntity) {
        return globalAttributeCalculator.calculateActualValue(userEntity, BasicAttribute.HEALTH_REGENERATION).getValue();
    }

    private int calculateMaxHealth(final UserEntity userEntity) {
        return globalAttributeCalculator.calculateMaximumValue(userEntity, CombatAttribute.LIFE).getValue();
    }
}

package com.morethanheroic.swords.regeneration.service.calc;

import org.springframework.stereotype.Service;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManaRegenerationCalculator implements RegenerationCalculator {

    private final GlobalAttributeCalculator globalAttributeCalculator;

    @Override
    public int calculateRegeneration(UserEntity userEntity, int durationToCalculate) {
        int newMana = calculateNewMana(userEntity, durationToCalculate);
        int maxMana = calculateMaxMana(userEntity);

        return newMana > maxMana ? maxMana : newMana;
    }

    private int calculateNewMana(final UserEntity userEntity, final int durationToCalculate) {
        return userEntity.getManaPoints() + calculateManaRegeneration(userEntity) * durationToCalculate;
    }

    private int calculateManaRegeneration(final UserEntity userEntity) {
        return globalAttributeCalculator.calculateActualValue(userEntity, BasicAttribute.MANA_REGENERATION).getValue();
    }

    private int calculateMaxMana(final UserEntity userEntity) {
        return globalAttributeCalculator.calculateMaximumValue(userEntity, CombatAttribute.MANA).getValue();
    }
}

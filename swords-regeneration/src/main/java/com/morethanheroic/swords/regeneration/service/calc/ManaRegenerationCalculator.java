package com.morethanheroic.swords.regeneration.service.calc;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManaRegenerationCalculator implements RegenerationCalculator {

    private static final int MANA_REGENERATION_UNIT = 2;

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Override
    public int calculateRegeneration(UserEntity user, int durationToCalculate) {
        int newMana = user.getManaPoints() + MANA_REGENERATION_UNIT * durationToCalculate;
        int maxMana = globalAttributeCalculator.calculateMaximumValue(user, CombatAttribute.MANA).getValue();

        if (newMana > maxMana) {
            return maxMana;
        }

        return newMana;
    }
}

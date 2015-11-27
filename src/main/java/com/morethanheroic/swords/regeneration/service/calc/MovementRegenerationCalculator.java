package com.morethanheroic.swords.regeneration.service.calc;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovementRegenerationCalculator implements RegenerationCalculator {

    private static final int MOVEMENT_REGENERATION_UNIT = 1;

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Override
    public int calculateRegeneration(UserEntity user, int durationToCalculate) {
        int newMovement = user.getRegeneration().getMovementPoints() + MOVEMENT_REGENERATION_UNIT * durationToCalculate;
        int maxMovement = globalAttributeCalculator.calculateMaximumValue(user, BasicAttribute.MOVEMENT);

        if (newMovement > maxMovement) {
            return maxMovement;
        }

        return newMovement;
    }
}

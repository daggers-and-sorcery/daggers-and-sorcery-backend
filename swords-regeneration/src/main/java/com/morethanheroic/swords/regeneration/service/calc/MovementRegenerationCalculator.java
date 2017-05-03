package com.morethanheroic.swords.regeneration.service.calc;

import org.springframework.stereotype.Service;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovementRegenerationCalculator implements RegenerationCalculator {

    private final GlobalAttributeCalculator globalAttributeCalculator;

    @Override
    public int calculateRegeneration(UserEntity userEntity, int durationToCalculate) {
        int newMovement = calculateNewMovement(userEntity, durationToCalculate);
        int maxMovement = calculateMaxMovement(userEntity);

        return newMovement > maxMovement ? maxMovement : newMovement;
    }

    private int calculateNewMovement(final UserEntity userEntity, final int durationToCalculate) {
        return userEntity.getMovementPoints() + calculateMovementRegeneration(userEntity) * durationToCalculate;
    }

    private int calculateMovementRegeneration(final UserEntity userEntity) {
        return globalAttributeCalculator.calculateActualValue(userEntity, BasicAttribute.MOVEMENT_REGENERATION).getValue();
    }

    private int calculateMaxMovement(final UserEntity userEntity) {
        return globalAttributeCalculator.calculateMaximumValue(userEntity, BasicAttribute.MOVEMENT).getValue();
    }
}

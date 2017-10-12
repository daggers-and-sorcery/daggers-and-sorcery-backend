package com.morethanheroic.swords.attribute.service.manipulator;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.SimpleValueAttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.data.AttributeData;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Let you manipulate the user's basic attributes. It will take into account the user's maximum attribute and don't let
 * you set higher amounts than that.
 */
@Service
@Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.SERIALIZABLE)
@RequiredArgsConstructor
public class UserBasicAttributeManipulator {

    private final GlobalAttributeCalculator globalAttributeCalculator;

    public void increaseHealth(final UserEntity userEntity, final int amount) {
        final AttributeData attributeData = globalAttributeCalculator.calculateAttributeValue(userEntity, CombatAttribute.LIFE);

        if (attributeData.getActual().getValue() + amount > attributeData.getMaximum().getValue()) {
            userEntity.setHealthPoints(attributeData.getMaximum().getValue());
        } else {
            userEntity.setHealthPoints(attributeData.getActual().getValue() + amount);
        }
    }

    public void decreaseHealth(final UserEntity userEntity, final int amount) {
        final SimpleValueAttributeCalculationResult attributeCalculationResult = globalAttributeCalculator.calculateActualValue(userEntity, CombatAttribute.LIFE);

        if (attributeCalculationResult.getValue() - amount < 0) {
            userEntity.setHealthPoints(0);
        } else {
            userEntity.setHealthPoints(attributeCalculationResult.getValue() - amount);
        }
    }

    public void increaseMana(final UserEntity userEntity, final int amount) {
        final AttributeData attributeData = globalAttributeCalculator.calculateAttributeValue(userEntity, CombatAttribute.MANA);

        if (attributeData.getActual().getValue() + amount > attributeData.getMaximum().getValue()) {
            userEntity.setManaPoints(attributeData.getMaximum().getValue());
        } else {
            userEntity.setManaPoints(attributeData.getActual().getValue() + amount);
        }
    }

    public void decreaseMana(final UserEntity userEntity, final int amount) {
        final SimpleValueAttributeCalculationResult attributeCalculationResult = globalAttributeCalculator.calculateActualValue(userEntity, CombatAttribute.MANA);

        if (attributeCalculationResult.getValue() - amount < 0) {
            userEntity.setManaPoints(0);
        } else {
            userEntity.setManaPoints(attributeCalculationResult.getValue() - amount);
        }
    }

    public void increaseMovement(final UserEntity userEntity, final int amount) {
        final AttributeData attributeData = globalAttributeCalculator.calculateAttributeValue(userEntity, BasicAttribute.MOVEMENT);

        if (attributeData.getActual().getValue() + amount > attributeData.getMaximum().getValue()) {
            userEntity.setMovementPoints(attributeData.getMaximum().getValue());
        } else {
            userEntity.setMovementPoints(attributeData.getActual().getValue() + amount);
        }
    }

    public void decreaseMovement(final UserEntity userEntity, final int amount) {
        final SimpleValueAttributeCalculationResult attributeCalculationResult = globalAttributeCalculator.calculateActualValue(userEntity, BasicAttribute.MOVEMENT);

        if (attributeCalculationResult.getValue() - amount < 0) {
            userEntity.setMovementPoints(0);
        } else {
            userEntity.setMovementPoints(attributeCalculationResult.getValue() - amount);
        }
    }
}

package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.data.AttributeData;
import com.morethanheroic.swords.attribute.service.modifier.calculator.GlobalAttributeModifierCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Used to calculate a {@link com.morethanheroic.swords.attribute.domain.type.AttributeType#BASIC} attribute's all data related to the player.
 */
@Service
@RequiredArgsConstructor
public class BasicAttributeCalculator extends GenericAttributeCalculator<BasicAttribute> {

    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final GlobalAttributeModifierCalculator globalAttributeModifierCalculator;

    @Override
    public AttributeData calculateAttributeValue(final UserEntity user, final BasicAttribute attribute) {
        return AttributeData.attributeDataBuilder()
                .attribute(attribute)
                .actual(globalAttributeCalculator.calculateActualValue(user, attribute))
                .maximum(globalAttributeCalculator.calculateMaximumValue(user, attribute))
                .modifierData(globalAttributeModifierCalculator.calculateModifierData(user, attribute))
                .build();
    }

    @Override
    public AttributeCalculationResult calculateActualValue(final UserEntity user, final Attribute attribute, final boolean shouldCheckMinimum) {
        if (attribute == BasicAttribute.MOVEMENT) {
            return new AttributeCalculationResult(user.getMovementPoints(), attribute);
        }

        return super.calculateActualValue(user, attribute, shouldCheckMinimum);
    }

    @Override
    public AttributeCalculationResult calculateActualBeforePercentageMultiplication(final UserEntity user, final Attribute attribute) {
        final AttributeCalculationResult result = super.calculateActualBeforePercentageMultiplication(user, attribute);

        result.increaseValue(attribute.getInitialValue());

        return result;
    }

    public Class<BasicAttribute> getSupportedType() {
        return BasicAttribute.class;
    }
}

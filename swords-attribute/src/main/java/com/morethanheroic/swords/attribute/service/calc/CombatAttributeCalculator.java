package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.data.AttributeData;
import com.morethanheroic.swords.attribute.service.modifier.calculator.GlobalAttributeModifierCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Used to calculate a {@link com.morethanheroic.swords.attribute.domain.type.AttributeType#COMBAT} attribute's all data related to the player.
 */
@Service
public class CombatAttributeCalculator implements AttributeCalculator<CombatAttribute> {

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private GlobalAttributeModifierCalculator globalAttributeModifierCalculator;

    @Override
    public AttributeData calculateAttributeValue(UserEntity user, CombatAttribute attribute) {
        return AttributeData.attributeDataBuilder()
                .attribute(attribute)
                .actual(globalAttributeCalculator.calculateActualValue(user, attribute))
                .maximum(globalAttributeCalculator.calculateMaximumValue(user, attribute))
                .modifierData(globalAttributeModifierCalculator.calculateModifierData(user, attribute))
                .build();
    }

    @Override
    public Class<CombatAttribute> getSupportedType() {
        return CombatAttribute.class;
    }

    public int calculateAllBonusByGeneralAttributes(UserEntity user, CombatAttribute attribute) {
        int result = 0;

        for (GeneralAttribute target : attribute.getBonusAttributes()) {
            result += calculateBonusByGeneralAttribute(user, target, attribute.getBonusPercentage());
        }

        return result;
    }

    public int calculateBonusByGeneralAttribute(UserEntity user, GeneralAttribute attribute, double bonusPercentage) {
        return (int) Math.floor(globalAttributeCalculator.calculateActualValue(user, attribute).getValue() * bonusPercentage);
    }

    public int calculateMinimumAttributeBonuses(UserEntity userEntity, CombatAttribute attribute) {
        if (attribute.getMinimalValue() == 0) {
            return 0;
        }

        final AttributeCalculationResult originalAttributeValue = globalAttributeCalculator.calculateActualValue(userEntity, attribute, false);

        if (originalAttributeValue.getValue() < attribute.getMinimalValue()) {
            return attribute.getMinimalValue() - originalAttributeValue.getValue();
        }

        return 0;
    }
}

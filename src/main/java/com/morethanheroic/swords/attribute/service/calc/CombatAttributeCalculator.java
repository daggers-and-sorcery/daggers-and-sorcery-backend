package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeData;
import com.morethanheroic.swords.attribute.service.modifier.calculator.GlobalAttributeModifierCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CombatAttributeCalculator implements AttributeCalculator<CombatAttribute> {

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private GlobalAttributeModifierCalculator globalAttributeModifierCalculator;

    @Override
    public AttributeData calculateAttributeValue(UserEntity user, CombatAttribute attribute) {
        AttributeData.AttributeDataBuilder attributeDataBuilder = new AttributeData.AttributeDataBuilder(attribute);

        attributeDataBuilder.setActual(globalAttributeCalculator.calculateActualValue(user, attribute));
        attributeDataBuilder.setMaximum(globalAttributeCalculator.calculateMaximumValue(user, attribute));
        attributeDataBuilder.setAttributeModifierData(globalAttributeModifierCalculator.calculateModifierData(user, attribute));

        return attributeDataBuilder.build();
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
}

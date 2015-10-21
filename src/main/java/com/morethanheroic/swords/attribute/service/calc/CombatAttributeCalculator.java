package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.enums.Attribute;
import com.morethanheroic.swords.attribute.model.AttributeData;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CombatAttributeCalculator implements AttributeCalculator {

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private GeneralAttributeCalculator generalAttributeCalculator;

    @Autowired
    private AttributeModifierCalculator attributeModifierCalculator;

    @Override
    public AttributeData calculateAttributeValue(UserEntity user, Attribute attribute) {
        if (!(attribute instanceof CombatAttribute)) {
            throw new IllegalArgumentException("The attribute must be an instance of CombatAttribute.");
        }

        AttributeData.AttributeDataBuilder attributeDataBuilder = new AttributeData.AttributeDataBuilder(attribute);

        attributeDataBuilder.setActual(globalAttributeCalculator.calculateActualValue(user, attribute));
        attributeDataBuilder.setMaximum(globalAttributeCalculator.calculateMaximumValue(user, attribute));
        attributeDataBuilder.setAttributeModifierDataArray(attributeModifierCalculator.calculateModifierData(user, attribute));

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

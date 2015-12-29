package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.service.AttributeFacade;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeData;
import com.morethanheroic.swords.attribute.service.modifier.calculator.GlobalAttributeModifierCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CombatAttributeCalculator implements AttributeCalculator<CombatAttribute> {

    @Autowired
    private AttributeFacade attributeFacade;

    @Override
    public AttributeData calculateAttributeValue(UserEntity user, CombatAttribute attribute) {
        return AttributeData.builder()
                .attribute(attribute)
                .actual(attributeFacade.calculateAttributeValue(user, attribute))
                .maximum(attributeFacade.calculateAttributeMaximumValue(user, attribute))
                .modifierDataArray(attributeFacade.calculateAttributeModifierData(user, attribute))
                .build();
    }

    public int calculateAllBonusByGeneralAttributes(UserEntity user, CombatAttribute attribute) {
        int result = 0;

        for (GeneralAttribute target : attribute.getBonusAttributes()) {
            result += calculateBonusByGeneralAttribute(user, target, attribute.getBonusPercentage());
        }

        return result;
    }

    public int calculateBonusByGeneralAttribute(UserEntity user, GeneralAttribute attribute, double bonusPercentage) {
        return (int) Math.floor(attributeFacade.calculateAttributeValue(user, attribute).getValue() * bonusPercentage);
    }
}

package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.service.AttributeFacade;
import com.morethanheroic.swords.attribute.service.calc.domain.data.AttributeData;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Used to calculate a {@link com.morethanheroic.swords.attribute.domain.type.AttributeType#COMBAT} attribute's all data related to the player.
 */
@Service
public class CombatAttributeCalculator implements AttributeCalculator<CombatAttribute> {

    @Autowired
    private AttributeFacade attributeFacade;

    @Override
    public AttributeData calculateAttributeValue(UserEntity user, CombatAttribute attribute) {
        return AttributeData.attributeDataBuilder()
                .attribute(attribute)
                .actual(attributeFacade.calculateAttributeValue(user, attribute))
                .maximum(attributeFacade.calculateAttributeMaximumValue(user, attribute))
                .modifierData(attributeFacade.calculateAttributeModifierData(user, attribute))
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
        return (int) Math.floor(attributeFacade.calculateAttributeValue(user, attribute).getValue() * bonusPercentage);
    }
}

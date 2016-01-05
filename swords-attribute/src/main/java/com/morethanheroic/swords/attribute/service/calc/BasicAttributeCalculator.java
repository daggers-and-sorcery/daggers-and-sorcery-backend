package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.service.AttributeFacade;
import com.morethanheroic.swords.attribute.service.calc.domain.data.AttributeData;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Used to calculate a {@link com.morethanheroic.swords.attribute.domain.type.AttributeType#BASIC} attribute's all data related to the player.
 */
@Service
public class BasicAttributeCalculator implements AttributeCalculator<BasicAttribute> {

    @Autowired
    private AttributeFacade attributeFacade;

    @Override
    public AttributeData calculateAttributeValue(UserEntity user, BasicAttribute attribute) {
        return AttributeData.attributeDataBuilder()
                .attribute(attribute)
                .actual(attributeFacade.calculateAttributeValue(user, attribute))
                .maximum(attributeFacade.calculateAttributeMaximumValue(user, attribute))
                .modifierData(attributeFacade.calculateAttributeModifierData(user, attribute))
                .build();
    }

    public Class<BasicAttribute> getSupportedType() {
        return BasicAttribute.class;
    }
}

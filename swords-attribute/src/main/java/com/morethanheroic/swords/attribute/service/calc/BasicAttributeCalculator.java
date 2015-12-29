package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeData;
import com.morethanheroic.swords.attribute.service.modifier.calculator.GlobalAttributeModifierCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicAttributeCalculator implements AttributeCalculator<BasicAttribute> {

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private GlobalAttributeModifierCalculator globalAttributeModifierCalculator;

    @Override
    public AttributeData calculateAttributeValue(UserEntity user, BasicAttribute attribute) {
        return AttributeData.builder()
                .attribute(attribute)
                .actual(globalAttributeCalculator.calculateActualValue(user, attribute))
                .maximum(globalAttributeCalculator.calculateMaximumValue(user, attribute))
                .modifierData(globalAttributeModifierCalculator.calculateModifierData(user, attribute))
                .build();
    }
}

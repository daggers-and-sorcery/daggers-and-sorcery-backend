package com.morethanheroic.swords.attribute.service;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.data.AttributeData;
import com.morethanheroic.swords.attribute.service.modifier.calculator.GlobalAttributeModifierCalculator;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @deprecated Use the appropriate classes directly instead.
 */
@Service
@Deprecated
public class AttributeFacade {

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private GlobalAttributeModifierCalculator globalAttributeModifierCalculator;

    public AttributeCalculationResult calculateAttributeValue(UserEntity userEntity, Attribute attribute) {
        return globalAttributeCalculator.calculateActualValue(userEntity, attribute);
    }

    public AttributeCalculationResult calculateAttributeMaximumValue(UserEntity userEntity, Attribute attribute) {
        return globalAttributeCalculator.calculateMaximumValue(userEntity, attribute);
    }

    public List<AttributeModifierEntry> calculateAttributeModifierData(UserEntity user, Attribute attribute) {
        return globalAttributeModifierCalculator.calculateModifierData(user, attribute);
    }
}

package com.morethanheroic.swords.attribute.domain;

import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeCalculationResult;
import com.morethanheroic.swords.user.domain.UserEntity;

public class AttributeEntity {

    private final UserEntity userEntity;
    private final GlobalAttributeCalculator globalAttributeCalculator;

    public AttributeEntity(UserEntity userEntity, GlobalAttributeCalculator globalAttributeCalculator) {
        this.userEntity = userEntity;
        this.globalAttributeCalculator = globalAttributeCalculator;
    }

    public AttributeCalculationResult getActualAttributeValue(Attribute attribute) {
        return globalAttributeCalculator.calculateActualValue(userEntity, attribute);
    }

    public AttributeCalculationResult getMaximumAttributeValue(Attribute attribute) {
        return globalAttributeCalculator.calculateMaximumValue(userEntity, attribute);
    }
}

package com.morethanheroic.swords.attribute.service;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeData;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeFacade {

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    public AttributeData getAttributeData(UserEntity userEntity, Attribute attribute) {
        return globalAttributeCalculator.calculateAttributeValue(userEntity, attribute);
    }

    public AttributeCalculationResult calculateAttributeValue(UserEntity userEntity, Attribute attribute) {
        return globalAttributeCalculator.calculateActualValue(userEntity, attribute);
    }

    public AttributeCalculationResult calculateAttributeMaximumValue(UserEntity userEntity, Attribute attribute) {
        return globalAttributeCalculator.calculateMaximumValue(userEntity, attribute);
    }
}

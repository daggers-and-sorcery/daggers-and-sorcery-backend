package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeData;
import com.morethanheroic.swords.user.domain.UserEntity;

public interface AttributeCalculator {

    AttributeData calculateAttributeValue(UserEntity user, Attribute attribute);
}

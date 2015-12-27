package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeData;
import com.morethanheroic.swords.user.domain.UserEntity;

public interface AttributeCalculator<T extends Attribute> {

    AttributeData calculateAttributeValue(UserEntity user, T attribute);
}

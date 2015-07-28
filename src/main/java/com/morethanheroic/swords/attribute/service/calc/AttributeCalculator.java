package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.enums.Attribute;
import com.morethanheroic.swords.attribute.model.AttributeData;
import com.morethanheroic.swords.user.domain.UserEntity;

public interface AttributeCalculator {

    AttributeData calculateAttributeValue(UserEntity user, Attribute attribute);
}

package com.morethanheroic.swords.attribute.service.bonus;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeCalculationResult;
import com.morethanheroic.swords.user.domain.UserEntity;

public interface AttributeBonusProvider {

    AttributeCalculationResult calculateBonus(UserEntity userEntity, Attribute attribute);
}

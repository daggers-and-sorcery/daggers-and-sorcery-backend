package com.morethanheroic.swords.attribute.service.bonus;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.AttributeCalculationResult;
import com.morethanheroic.swords.user.domain.UserEntity;

/**
 * Attribute providers are automatically looked after to calculate an attribute's bonuses. It's a good way for some higher module
 * to create its own AttributeBonusProvider to be able to modify the attribute variables without the attribute module knowing about
 * the module at all. You only need to create a {@link org.springframework.stereotype.Service} from this class to be automatically
 * registered.
 */
public interface AttributeBonusProvider {

    AttributeCalculationResult calculateBonus(UserEntity userEntity, Attribute attribute);
}

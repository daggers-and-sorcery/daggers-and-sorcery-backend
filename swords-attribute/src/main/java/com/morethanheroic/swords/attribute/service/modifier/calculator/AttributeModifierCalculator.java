package com.morethanheroic.swords.attribute.service.modifier.calculator;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.user.domain.UserEntity;

import java.util.List;

/**
 * Holds the step by step data of the modifications used while calculated an attribute's value.
 *
 * @param <T> The {@link com.morethanheroic.swords.attribute.domain.type.AttributeType} of the attribute this calculator belongs to.
 */
public interface AttributeModifierCalculator<T extends Attribute> {

    List<AttributeModifierEntry> calculate(UserEntity user, T attribute);
}

package com.morethanheroic.swords.attribute.service.modifier.calculator;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.user.domain.UserEntity;

import java.util.List;

public interface AttributeModifierCalculator<T extends Attribute> {

    List<AttributeModifierEntry> calculate(UserEntity user, T attribute);
}

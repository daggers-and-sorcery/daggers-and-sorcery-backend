package com.morethanheroic.swords.attribute.service.modifier.calculator;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierUnitType;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierValue;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicAttributeModifierCalculator implements AttributeModifierCalculator<BasicAttribute> {

    @Override
    public List<AttributeModifierEntry> calculate(UserEntity user, BasicAttribute attribute) {
        return Lists.newArrayList(
                new AttributeModifierEntry(AttributeModifierType.INITIAL, AttributeModifierUnitType.VALUE, new AttributeModifierValue(attribute.getInitialValue()))
        );
    }
}

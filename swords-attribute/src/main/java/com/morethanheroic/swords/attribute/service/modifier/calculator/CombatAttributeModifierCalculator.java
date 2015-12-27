package com.morethanheroic.swords.attribute.service.modifier.calculator;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierValueType;
import com.morethanheroic.swords.attribute.service.calc.CombatAttributeCalculator;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierValue;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombatAttributeModifierCalculator implements AttributeModifierCalculator<CombatAttribute> {

    @Autowired
    private CombatAttributeCalculator combatAttributeCalculator;

    @Override
    public List<AttributeModifierEntry> calculate(UserEntity user, CombatAttribute attribute) {
        return Lists.newArrayList(
                new AttributeModifierEntry(AttributeModifierType.GENERAL_ATTRIBUTE, AttributeModifierValueType.VALUE, new AttributeModifierValue(combatAttributeCalculator.calculateAllBonusByGeneralAttributes(user, attribute))),
                new AttributeModifierEntry(AttributeModifierType.INITIAL, AttributeModifierValueType.VALUE, new AttributeModifierValue(attribute.getInitialValue()))
        );
    }
}

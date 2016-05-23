package com.morethanheroic.swords.attribute.service.modifier.calculator;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierUnitType;
import com.morethanheroic.swords.attribute.service.calc.CombatAttributeCalculator;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierValue;
import com.morethanheroic.swords.attribute.service.modifier.domain.CombatAttributeModifierValue;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombatAttributeModifierCalculator implements AttributeModifierCalculator<CombatAttribute> {

    @Autowired
    private CombatAttributeCalculator combatAttributeCalculator;

    @Override
    public List<AttributeModifierEntry> calculate(UserEntity userEntity, CombatAttribute attribute) {
        return Lists.newArrayList(
                new AttributeModifierEntry(AttributeModifierType.GENERAL_ATTRIBUTE, AttributeModifierUnitType.VALUE, new CombatAttributeModifierValue(combatAttributeCalculator.calculateAllBonusByGeneralAttributes(userEntity, attribute))),
                new AttributeModifierEntry(AttributeModifierType.INITIAL, AttributeModifierUnitType.VALUE, new CombatAttributeModifierValue(attribute.getInitialValue())),
                new AttributeModifierEntry(AttributeModifierType.MINIMUM, AttributeModifierUnitType.VALUE, new CombatAttributeModifierValue(combatAttributeCalculator.calculateMinimumAttributeBonuses(userEntity, attribute)))
        );
    }
}

package com.morethanheroic.swords.attribute.service.modifier.calculator;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierUnitType;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierValue;
import com.morethanheroic.swords.attribute.service.modifier.domain.PercentageAttributeModifierEntry;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RaceAttributeModifierCalculator implements AttributeModifierCalculator {

    private static final int NO_RACIAL_MODIFIER = 0;

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Override
    public List<AttributeModifierEntry> calculate(UserEntity user, Attribute attribute) {
        int racialModifierPercentage = NO_RACIAL_MODIFIER;

        if (attribute instanceof GeneralAttribute) {
            racialModifierPercentage = globalAttributeCalculator.getRacialModifierValue(user.getRace(), (GeneralAttribute) attribute);
        }

        if (racialModifierPercentage != NO_RACIAL_MODIFIER) {
            final int racialModifierValue = globalAttributeCalculator.calculatePercentageModifiedAttribute(globalAttributeCalculator.calculateActualBeforePercentageMultiplication(user, attribute), racialModifierPercentage).getValue() - globalAttributeCalculator.calculateActualBeforePercentageMultiplication(user, attribute).getValue();

            return Lists.newArrayList(
                    new PercentageAttributeModifierEntry(AttributeModifierType.RACIAL, AttributeModifierUnitType.PERCENTAGE, new AttributeModifierValue(racialModifierValue), racialModifierPercentage)
            );
        }

        return Collections.emptyList();
    }
}

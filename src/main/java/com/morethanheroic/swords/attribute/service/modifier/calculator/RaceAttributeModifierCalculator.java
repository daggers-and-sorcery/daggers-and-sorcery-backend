package com.morethanheroic.swords.attribute.service.modifier.calculator;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierValueType;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierValue;
import com.morethanheroic.swords.attribute.service.modifier.domain.PercentageAttributeModifierEntry;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RaceAttributeModifierCalculator implements AttributeModifierCalculator {

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Override
    public List<AttributeModifierEntry> calculate(UserEntity user, Attribute attribute) {
        int racialModifierPercentage = user.getRace().getRacialModifier(attribute);

        if (racialModifierPercentage != Race.NO_RACIAL_MODIFIER) {
            int racialModifierValue = globalAttributeCalculator.calculatePercentageModifiedAttribute(globalAttributeCalculator.calculateActualBeforePercentageMultiplication(user, attribute), racialModifierPercentage).getValue() - globalAttributeCalculator.calculateActualBeforePercentageMultiplication(user, attribute).getValue();

            return Lists.newArrayList(
                    new PercentageAttributeModifierEntry(AttributeModifierType.RACIAL, AttributeModifierValueType.PERCENTAGE, new AttributeModifierValue(racialModifierValue), racialModifierPercentage)
            );
        }

        return Collections.emptyList();
    }
}

package com.morethanheroic.swords.attribute.service.modifier.calculator;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierValueType;
import com.morethanheroic.swords.attribute.service.AttributeToRacialModifierConverter;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierValue;
import com.morethanheroic.swords.attribute.service.modifier.domain.PercentageAttributeModifierEntry;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.race.model.RaceEntity;
import com.morethanheroic.swords.race.model.modifier.RacialModifier;
import com.morethanheroic.swords.race.model.modifier.entry.NumericRacialModifierEntry;
import com.morethanheroic.swords.race.service.RaceEntityCache;
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

    @Autowired
    private AttributeToRacialModifierConverter attributeToRacialModifierConverter;

    @Autowired
    private RaceEntityCache raceEntityCache;

    @Override
    public List<AttributeModifierEntry> calculate(UserEntity user, Attribute attribute) {
        int racialModifierPercentage = NO_RACIAL_MODIFIER;

        if (attribute instanceof GeneralAttribute) {
            racialModifierPercentage = getRacialModifierValue(user.getRace(), (GeneralAttribute) attribute);
        }

        if (racialModifierPercentage != NO_RACIAL_MODIFIER) {
            final int racialModifierValue = globalAttributeCalculator.calculatePercentageModifiedAttribute(globalAttributeCalculator.calculateActualBeforePercentageMultiplication(user, attribute), racialModifierPercentage).getValue() - globalAttributeCalculator.calculateActualBeforePercentageMultiplication(user, attribute).getValue();

            return Lists.newArrayList(
                    new PercentageAttributeModifierEntry(AttributeModifierType.RACIAL, AttributeModifierValueType.PERCENTAGE, new AttributeModifierValue(racialModifierValue), racialModifierPercentage)
            );
        }

        return Collections.emptyList();
    }

    private int getRacialModifierValue(Race race, GeneralAttribute attribute) {
        final RacialModifier racialModifier = attributeToRacialModifierConverter.convert(attribute);
        final RaceEntity raceEntity = raceEntityCache.getRaceEntity(race);

        return ((NumericRacialModifierEntry) raceEntity.getRacialModifier(racialModifier)).getValue();
    }
}

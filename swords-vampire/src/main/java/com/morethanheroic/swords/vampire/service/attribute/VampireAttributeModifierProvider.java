package com.morethanheroic.swords.vampire.service.attribute;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierUnitType;
import com.morethanheroic.swords.attribute.service.modifier.calculator.AttributeModifierProvider;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierValue;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.vampire.service.VampireCalculator;

import lombok.RequiredArgsConstructor;

/**
 * Calculate the +10 life modifier gained from being vampire.
 */
@Service
@RequiredArgsConstructor
public class VampireAttributeModifierProvider implements AttributeModifierProvider {

    private static final int VAMPIRE_BONUS = 10;

    private final VampireCalculator vampireCalculator;

    /**
     * Provide the modifiers life points if the player is a vampire.
     *
     * @param userEntity the user we are generating the modifiers for
     * @param attribute  the attribute we are generating the modifiers for
     * @return the provided modifiers
     */
    @Override
    public List<AttributeModifierEntry> calculateModifiers(final UserEntity userEntity, final Attribute attribute) {
        if (attribute == CombatAttribute.LIFE && vampireCalculator.isVampire(userEntity)) {
            return Lists.newArrayList(new AttributeModifierEntry(AttributeModifierType.VAMPIRE, AttributeModifierUnitType.VALUE, new AttributeModifierValue(VAMPIRE_BONUS)));
        }

        return Collections.emptyList();
    }
}

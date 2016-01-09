package com.morethanheroic.swords.profile.service.response;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeType;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.CombatAttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierValue;
import com.morethanheroic.swords.attribute.service.modifier.domain.CombatAttributeModifierValue;
import com.morethanheroic.swords.response.service.PartialResponseCollectionBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AttributeModifierPartialResponseBuilder implements PartialResponseCollectionBuilder<AttributeModifierPartialResponseBuilderConfiguration> {

    @Override
    public Collection<AttributeModifierPartialResponse> build(AttributeModifierPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final List<AttributeModifierPartialResponse> result = new ArrayList<>();

        for (AttributeModifierEntry attributeModifierEntry : responseBuilderConfiguration.getModifierData()) {
            result.add(
                    AttributeModifierPartialResponse.builder()
                            .attributeModifierType(attributeModifierEntry.getAttributeModifierType())
                            .attributeModifierValueType(attributeModifierEntry.getAttributeModifierUnitType())
                            .attributeModifierValue(buildAttributeModifierValue(responseBuilderConfiguration.getAttribute(), attributeModifierEntry))
                            .build()
            );
        }

        return result;
    }

    private String buildAttributeModifierValue(Attribute attribute, AttributeModifierEntry attributeModifierEntry) {
        if (attribute.getAttributeType() == AttributeType.COMBAT) {
            return formatCombatAttributeModifier(attributeModifierEntry.getAttributeModifierValue());
        } else {
            return String.valueOf(attributeModifierEntry.getAttributeModifierValue().getValue());
        }
    }

    //TODO: write own formatters for this or move it to JS side
    private String formatCombatAttributeModifier(AttributeModifierValue attributeModifierValue) {
        String result = String.valueOf(attributeModifierValue.getValue());

        if (attributeModifierValue instanceof CombatAttributeModifierValue) {
            final CombatAttributeModifierValue combatAttributeModifierValue = (CombatAttributeModifierValue) attributeModifierValue;

            if (combatAttributeModifierValue.getD2() > 0) {
                result += " + " + combatAttributeModifierValue.getD2() + "d2";
            }
            if (combatAttributeModifierValue.getD4() > 0) {
                result += " + " + combatAttributeModifierValue.getD4() + "d4";
            }
            if (combatAttributeModifierValue.getD6() > 0) {
                result += " + " + combatAttributeModifierValue.getD6() + "d6";
            }
            if (combatAttributeModifierValue.getD8() > 0) {
                result += " + " + combatAttributeModifierValue.getD8() + "d8";
            }
            if (combatAttributeModifierValue.getD10() > 0) {
                result += " + " + combatAttributeModifierValue.getD10() + "d10";
            }
        }

        return result;
    }

    private String formatCombatAttributeModifier(AttributeCalculationResult attributeModifierValue) {
        String result = String.valueOf(attributeModifierValue.getValue());

        if (attributeModifierValue instanceof CombatAttributeCalculationResult) {
            final CombatAttributeCalculationResult combatAttributeCalculationResult = (CombatAttributeCalculationResult) attributeModifierValue;

            if (combatAttributeCalculationResult.getD2() > 0) {
                result += " + " + combatAttributeCalculationResult.getD2() + "d2";
            }
            if (combatAttributeCalculationResult.getD4() > 0) {
                result += " + " + combatAttributeCalculationResult.getD4() + "d4";
            }
            if (combatAttributeCalculationResult.getD6() > 0) {
                result += " + " + combatAttributeCalculationResult.getD6() + "d6";
            }
            if (combatAttributeCalculationResult.getD8() > 0) {
                result += " + " + combatAttributeCalculationResult.getD8() + "d8";
            }
            if (combatAttributeCalculationResult.getD10() > 0) {
                result += " + " + combatAttributeCalculationResult.getD10() + "d10";
            }
        }

        return result;
    }
}

package com.morethanheroic.swords.attribute.service.calc.domain;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Holds all attribute data after a full attribute calculation for a {@link com.morethanheroic.swords.attribute.domain.type.AttributeType#GENERAL} attribute.
 * Creating this class is very resource intensive so try to avoid it as much as possible.
 */
@Getter
public class GeneralAttributeData extends AttributeData {

    private final int pointsToNextLevel;

    @Builder(builderMethodName = "generalAttributeDataBuilder")
    private GeneralAttributeData(Attribute attribute, AttributeCalculationResult actual, AttributeCalculationResult maximum, List<AttributeModifierEntry> modifierData, int pointsToNextLevel) {
        super(attribute, actual, maximum, modifierData);

        this.pointsToNextLevel = pointsToNextLevel;
    }
}

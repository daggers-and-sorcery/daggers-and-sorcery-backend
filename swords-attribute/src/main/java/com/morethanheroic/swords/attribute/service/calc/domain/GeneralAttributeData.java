package com.morethanheroic.swords.attribute.service.calc.domain;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import lombok.Builder;

import java.util.List;

/**
 * Holds all attribute data after a full attribute calculation for a {@link com.morethanheroic.swords.attribute.domain.type.AttributeType#GENERAL} attribute.
 * Creating this class is very resource intensive so try to avoid it as much as possible.
 */
public class GeneralAttributeData extends AttributeData {

    private final int pointsToNextLevel;

    //At the moment lombok doesn't support builders correctly in inheritance. This should be fixed when lombok issue a fix (if this happens ever)
    @Builder(builderMethodName = "generalAttributeDataBuilder")
    private GeneralAttributeData(Attribute attribute, AttributeCalculationResult actual, AttributeCalculationResult maximum, List<AttributeModifierEntry> modifierData, int pointsToNextLevel) {
        super(attribute, actual, maximum, modifierData);

        this.pointsToNextLevel = pointsToNextLevel;
    }

    public int getPointsToNextLevel() {
        return pointsToNextLevel;
    }
}

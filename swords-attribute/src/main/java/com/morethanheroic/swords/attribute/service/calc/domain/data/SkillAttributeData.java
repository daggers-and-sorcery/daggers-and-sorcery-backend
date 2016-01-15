package com.morethanheroic.swords.attribute.service.calc.domain.data;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Holds all attribute data after a full attribute calculation for a {@link com.morethanheroic.swords.attribute.domain.type.AttributeType#SKILL} attribute.
 * Creating this class is very resource intensive so try to avoid it as much as possible.
 */
@Getter
public class SkillAttributeData extends AttributeData {

    private final int actualXp;
    private final int nextLevelXp;
    private final int xpBetweenLevels;

    @Builder(builderMethodName = "skillAttributeDataBuilder")
    private SkillAttributeData(Attribute attribute, AttributeCalculationResult actual, AttributeCalculationResult maximum, List<AttributeModifierEntry> attributeModifierData, int actualXp, int nextLevelXp, int xpBetweenLevels) {
        super(attribute, actual, maximum, attributeModifierData);

        this.actualXp = actualXp;
        this.nextLevelXp = nextLevelXp;
        this.xpBetweenLevels = xpBetweenLevels;
    }
}

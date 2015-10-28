package com.morethanheroic.swords.attribute.service.calc.domain;

import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;

import java.util.List;

public class GeneralAttributeData extends AttributeData {

    private final int pointsToNextLevel;

    protected GeneralAttributeData(Attribute attribute, AttributeCalculationResult actual, int maximum, List<AttributeModifierEntry> attributeModifierEntryArray, int pointsToNextLevel) {
        super(attribute, actual, maximum, attributeModifierEntryArray);

        this.pointsToNextLevel = pointsToNextLevel;
    }

    public int getPointsToNextLevel() {
        return pointsToNextLevel;
    }

    public static class GeneralAttributeDataBuilder extends AttributeDataBuilder {

        private int pointsToNextLevel;

        public GeneralAttributeDataBuilder(GeneralAttribute attribute) {
            super(attribute);
        }

        public int getPointsToNextLevel() {
            return pointsToNextLevel;
        }

        public void setPointsToNextLevel(int pointsToNextLevel) {
            this.pointsToNextLevel = pointsToNextLevel;
        }

        public GeneralAttributeData build() {
            return new GeneralAttributeData(attribute, actual, maximum, attributeModifierArray, pointsToNextLevel);
        }
    }
}

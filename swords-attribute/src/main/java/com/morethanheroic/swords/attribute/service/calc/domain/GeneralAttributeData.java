package com.morethanheroic.swords.attribute.service.calc.domain;

import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;

import java.util.List;

public class GeneralAttributeData extends AttributeData {

    private final int pointsToNextLevel;

    protected GeneralAttributeData(Attribute attribute, AttributeCalculationResult actual, AttributeCalculationResult maximum, List<AttributeModifierEntry> attributeModifierEntryArray, int pointsToNextLevel) {
        super(attribute, actual, maximum, attributeModifierEntryArray);

        this.pointsToNextLevel = pointsToNextLevel;
    }

    public int getPointsToNextLevel() {
        return pointsToNextLevel;
    }

    public static class GeneralAttributeDataBuilder {

        private int pointsToNextLevel;

        protected Attribute attribute;
        protected AttributeCalculationResult maximum;
        protected AttributeCalculationResult actual;
        protected List<AttributeModifierEntry> attributeModifierArray;

        public GeneralAttributeDataBuilder(GeneralAttribute attribute) {
            this.attribute = attribute;
        }

        public void setMaximum(AttributeCalculationResult maximum) {
            this.maximum = maximum;
        }

        public void setActual(AttributeCalculationResult actual) {
            this.actual = actual;
        }

        public void setAttributeModifierData(List<AttributeModifierEntry> attributeModifierArray) {
            this.attributeModifierArray = attributeModifierArray;
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

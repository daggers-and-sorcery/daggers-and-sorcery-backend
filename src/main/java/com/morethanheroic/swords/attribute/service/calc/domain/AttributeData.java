package com.morethanheroic.swords.attribute.service.calc.domain;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierData;

public class AttributeData {

    private final AttributeCalculationResult actual;
    private final int maximum;
    private final AttributeModifierData[] modifierDataArray;
    private final Attribute attribute;

    public AttributeData(Attribute attribute, AttributeCalculationResult actual, int maximum, AttributeModifierData[] attributeModifierDataArray) {
        this.attribute = attribute;
        this.actual = actual;
        this.maximum = maximum;
        this.modifierDataArray = attributeModifierDataArray;
    }

    public int getMaximum() {
        return maximum;
    }

    public AttributeCalculationResult getActual() {
        return actual;
    }

    public AttributeModifierData[] getModifierDataArray() {
        return modifierDataArray;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public static class AttributeDataBuilder {

        protected Attribute attribute;
        protected int maximum;
        protected AttributeCalculationResult actual;
        protected AttributeModifierData[] attributeModifierArray;

        public AttributeDataBuilder(Attribute attribute) {
            this.attribute = attribute;
        }

        public void setMaximum(int maximum) {
            this.maximum = maximum;
        }

        public void setActual(AttributeCalculationResult actual) {
            this.actual = actual;
        }

        public void setAttributeModifierDataArray(AttributeModifierData[] attributeModifierArray) {
            this.attributeModifierArray = attributeModifierArray;
        }

        public AttributeData build() {
            return new AttributeData(attribute, actual, maximum, attributeModifierArray);
        }
    }
}

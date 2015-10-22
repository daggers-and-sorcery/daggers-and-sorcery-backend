package com.morethanheroic.swords.attribute.service.calc.domain;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;

public class AttributeData {

    private final AttributeCalculationResult actual;
    private final int maximum;
    private final AttributeModifierEntry[] modifierDataArray;
    private final Attribute attribute;

    public AttributeData(Attribute attribute, AttributeCalculationResult actual, int maximum, AttributeModifierEntry[] attributeModifierEntryArray) {
        this.attribute = attribute;
        this.actual = actual;
        this.maximum = maximum;
        this.modifierDataArray = attributeModifierEntryArray;
    }

    public int getMaximum() {
        return maximum;
    }

    public AttributeCalculationResult getActual() {
        return actual;
    }

    public AttributeModifierEntry[] getModifierDataArray() {
        return modifierDataArray;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public static class AttributeDataBuilder {

        protected Attribute attribute;
        protected int maximum;
        protected AttributeCalculationResult actual;
        protected AttributeModifierEntry[] attributeModifierArray;

        public AttributeDataBuilder(Attribute attribute) {
            this.attribute = attribute;
        }

        public void setMaximum(int maximum) {
            this.maximum = maximum;
        }

        public void setActual(AttributeCalculationResult actual) {
            this.actual = actual;
        }

        public void setAttributeModifierDataArray(AttributeModifierEntry[] attributeModifierArray) {
            this.attributeModifierArray = attributeModifierArray;
        }

        public AttributeData build() {
            return new AttributeData(attribute, actual, maximum, attributeModifierArray);
        }
    }
}

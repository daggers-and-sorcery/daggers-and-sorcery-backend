package com.morethanheroic.swords.attribute.service.calc.domain;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;

import java.util.List;

public class AttributeData {

    private final AttributeCalculationResult actual;
    private final int maximum;
    private final List<AttributeModifierEntry> modifierDataArray;
    private final Attribute attribute;

    public AttributeData(Attribute attribute, AttributeCalculationResult actual, int maximum, List<AttributeModifierEntry> attributeModifierEntryArray) {
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

    public List<AttributeModifierEntry> getModifierDataArray() {
        return modifierDataArray;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public static class AttributeDataBuilder {

        protected Attribute attribute;
        protected int maximum;
        protected AttributeCalculationResult actual;
        protected List<AttributeModifierEntry> attributeModifierArray;

        public AttributeDataBuilder(Attribute attribute) {
            this.attribute = attribute;
        }

        public void setMaximum(int maximum) {
            this.maximum = maximum;
        }

        public void setActual(AttributeCalculationResult actual) {
            this.actual = actual;
        }

        public void setAttributeModifierData(List<AttributeModifierEntry> attributeModifierArray) {
            this.attributeModifierArray = attributeModifierArray;
        }

        public AttributeData build() {
            return new AttributeData(attribute, actual, maximum, attributeModifierArray);
        }
    }
}

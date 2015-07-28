package com.morethanheroic.swords.attribute.model;

import com.morethanheroic.swords.attribute.enums.Attribute;

public class AttributeData {

    private final int actual;
    private final int maximum;
    private final AttributeModifierData[] modifierDataArray;
    private final Attribute attribute;

    protected AttributeData(Attribute attribute, int actual, int maximum, AttributeModifierData[] attributeModifierDataArray) {
        this.attribute = attribute;
        this.actual = actual;
        this.maximum = maximum;
        this.modifierDataArray = attributeModifierDataArray;
    }

    public int getMaximum() {
        return maximum;
    }

    public int getActual() {
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
        protected int actual;
        protected AttributeModifierData[] attributeModifierArray;

        public AttributeDataBuilder(Attribute attribute) {
            this.attribute = attribute;
        }

        public void setMaximum(int maximum) {
            this.maximum = maximum;
        }

        public void setActual(int actual) {
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

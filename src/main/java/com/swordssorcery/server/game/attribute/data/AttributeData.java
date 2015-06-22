package com.swordssorcery.server.game.attribute.data;

public class AttributeData {

    private final int actual;
    private final int maximum;
    private final AttributeModifierData[] modifierDataArray;

    private AttributeData(int actual, int maximum, AttributeModifierData[] attributeModifierDataArray) {
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

    public static class AttributeDataBuilder {
        private int actual;
        private int maximum;
        private AttributeModifierData[] attributeModifierArray;

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
            return new AttributeData(actual, maximum, attributeModifierArray);
        }
    }
}

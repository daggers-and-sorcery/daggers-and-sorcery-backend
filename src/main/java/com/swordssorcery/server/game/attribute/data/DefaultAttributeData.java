package com.swordssorcery.server.game.attribute.data;

import java.util.ArrayList;

public class DefaultAttributeData implements AttributeData {

    private int actual;
    private int maximum;
    private ArrayList<AttributeModifierData> attributeModifierDatas = new ArrayList<>();

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public int getActual() {
        return actual;
    }

    public void setActual(int actual) {
        this.actual = actual;
    }

    public void addAttributeModifierData(AttributeModifierData attributeModifierData) {
        attributeModifierDatas.add(attributeModifierData);
    }

    public AttributeModifierData[] getAttributeModifierDataArray() {
        return attributeModifierDatas.toArray(new AttributeModifierData[attributeModifierDatas.size()]);
    }
}

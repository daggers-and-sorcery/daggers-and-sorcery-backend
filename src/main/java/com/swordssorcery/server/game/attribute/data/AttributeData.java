package com.swordssorcery.server.game.attribute.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AttributeData {

    private int actual;
    private int maximum;
    private List<AttributeModifierData> attributeModifierDatas = new ArrayList<>();

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

    public void setAttributeModifierDataArray(AttributeModifierData[] attributeModifierDataArray) {
        attributeModifierDatas = Arrays.asList(attributeModifierDataArray);
    }

    public AttributeModifierData[] getAttributeModifierDataArray() {
        return attributeModifierDatas.toArray(new AttributeModifierData[attributeModifierDatas.size()]);
    }
}

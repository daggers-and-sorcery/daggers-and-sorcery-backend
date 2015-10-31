package com.morethanheroic.swords.attribute.service.calc.domain;

import com.morethanheroic.swords.attribute.domain.Attribute;

public class AttributeCalculationResult {

    private Attribute attribute;
    private int value = 0;
    private int d2 = 0;
    private int d4 = 0;
    private int d6 = 0;
    private int d8 = 0;
    private int d10 = 0;

    public AttributeCalculationResult(Attribute attribute) {
        this(0, attribute);
    }

    public AttributeCalculationResult(int value, Attribute attribute) {
        this.value = value;
        this.attribute = attribute;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void increaseValue(int amount) {
        this.value += amount;
    }

    public int getD2() {
        return d2;
    }

    public void setD2(int d2) {
        this.d2 = d2;
    }

    public void increaseD2(int amount) {
        this.d2 += amount;
    }

    public int getD4() {
        return d4;
    }

    public void setD4(int d4) {
        this.d4 = d4;
    }

    public void increaseD4(int amount) {
        this.d4 += amount;
    }

    public int getD6() {
        return d6;
    }

    public void setD6(int d6) {
        this.d6 = d6;
    }

    public void increaseD6(int amount) {
        this.d6 += amount;
    }

    public int getD8() {
        return d8;
    }

    public void setD8(int d8) {
        this.d8 = d8;
    }

    public void increaseD8(int amount) {
        this.d8 += amount;
    }

    public int getD10() {
        return d10;
    }

    public void setD10(int d10) {
        this.d10 = d10;
    }

    public void increaseD10(int amount) {
        this.d10 += amount;
    }

    public void addCalculationResult(AttributeCalculationResult attributeCalculationResult) {
        this.value += attributeCalculationResult.value;
        this.d2 += attributeCalculationResult.d2;
        this.d4 += attributeCalculationResult.d4;
        this.d6 += attributeCalculationResult.d6;
        this.d8 += attributeCalculationResult.d8;
        this.d10 += attributeCalculationResult.d10;
    }

    public Attribute getAttribute() {
        return attribute;
    }
}

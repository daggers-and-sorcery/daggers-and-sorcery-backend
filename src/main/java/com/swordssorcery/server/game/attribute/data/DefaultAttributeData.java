package com.swordssorcery.server.game.attribute.data;

public class DefaultAttributeData implements AttributeData {

    private int actual;
    private int maximum;

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
}

package com.morethanheroic.swords.combat.domain;

public class DiceAttribute {

    private int value;
    private int d2;
    private int d4;
    private int d6;
    private int d8;
    private int d10;

    private DiceAttribute() {
    }

    public int getValue() {
        return value;
    }

    public int getD2() {
        return d2;
    }

    public int getD4() {
        return d4;
    }

    public int getD6() {
        return d6;
    }

    public int getD8() {
        return d8;
    }

    public int getD10() {
        return d10;
    }

    public static class DiceAttributeBuilder {

        private DiceAttribute diceAttribute;

        public DiceAttributeBuilder() {
            this.diceAttribute = new DiceAttribute();
        }

        public void setValue(int value) {
            diceAttribute.value = value;
        }

        public void setD2(int d2) {
            diceAttribute.d2 = d2;
        }

        public void setD4(int d4) {
            diceAttribute.d4 = d4;
        }

        public void setD6(int d6) {
            diceAttribute.d6 = d6;
        }

        public void setD8(int d8) {
            diceAttribute.d8 = d8;
        }

        public void setD10(int d10) {
            diceAttribute.d10 = d10;
        }

        public DiceAttribute build() {
            return diceAttribute;
        }
    }
}

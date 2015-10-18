package com.morethanheroic.swords.attribute.domain.modifier;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;

public class CombatAttributeModifierDefinition extends AttributeModifierDefinition {

    private CombatAttribute attribute;
    private int d2;
    private int d4;
    private int d6;
    private int d8;
    private int d10;

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

    public CombatAttribute getAttribute() {
        return attribute;
    }

    public static class CombatAttributeModifierDefinitionBuilder {

        private final CombatAttributeModifierDefinition combatAttributeModifierDefinition = new CombatAttributeModifierDefinition();

        public void setAttribute(CombatAttribute attribute) {
            combatAttributeModifierDefinition.attribute = attribute;
        }

        public void setAmount(int amount) {
            combatAttributeModifierDefinition.amount = amount;
        }

        public CombatAttributeModifierDefinition build() {
            return combatAttributeModifierDefinition;
        }

        public void setD2(int d2) {
            combatAttributeModifierDefinition.d2 = d2;
        }

        public void setD4(int d4) {
            combatAttributeModifierDefinition.d4 = d4;
        }

        public void setD6(int d6) {
            combatAttributeModifierDefinition.d6 = d6;
        }

        public void setD8(int d8) {
            combatAttributeModifierDefinition.d8 = d8;
        }

        public void setD10(int d10) {
            combatAttributeModifierDefinition.d10 = d10;
        }
    }
}
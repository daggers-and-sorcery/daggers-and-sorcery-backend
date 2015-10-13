package com.morethanheroic.swords.attribute.domain.modifier;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;

public class CombatAttributeModifierDefinition extends AttributeModifierDefinition {

    private CombatAttribute attribute;

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
    }
}
package com.morethanheroic.swords.attribute.domain.requirement;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;

public class CombatAttributeRequirementDefinition extends AttributeRequirementDefinition {

    private CombatAttribute attribute;

    public CombatAttribute getAttribute() {
        return attribute;
    }

    public static class CombatAttributeRequirementDefinitionBuilder {

        private final CombatAttributeRequirementDefinition combatAttributeRequirementDefinition = new CombatAttributeRequirementDefinition();

        public void setAttribute(CombatAttribute attribute) {
            combatAttributeRequirementDefinition.attribute = attribute;
        }

        public void setAmount(int amount) {
            combatAttributeRequirementDefinition.amount = amount;
        }

        public CombatAttributeRequirementDefinition build() {
            return combatAttributeRequirementDefinition;
        }
    }
}

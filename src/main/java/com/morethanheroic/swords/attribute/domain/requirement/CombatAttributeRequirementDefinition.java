package com.morethanheroic.swords.attribute.domain.requirement;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;

public class CombatAttributeRequirementDefinition extends AttributeRequirementDefinition {

    private CombatAttribute attribute;

    public CombatAttribute getAttribute() {
        return attribute;
    }

    public static class CombatAttributeRequirementDefinitionBuilder {

        private final CombatAttributeRequirementDefinition basicAttributeRequirementDefinition = new CombatAttributeRequirementDefinition();

        public void setAttribute(CombatAttribute attribute) {
            basicAttributeRequirementDefinition.attribute = attribute;
        }

        public void setAmount(int amount) {
            basicAttributeRequirementDefinition.amount = amount;
        }

        public CombatAttributeRequirementDefinition build() {
            return basicAttributeRequirementDefinition;
        }
    }
}

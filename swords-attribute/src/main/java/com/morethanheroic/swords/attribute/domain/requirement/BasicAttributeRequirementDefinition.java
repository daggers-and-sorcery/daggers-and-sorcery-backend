package com.morethanheroic.swords.attribute.domain.requirement;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;

public class BasicAttributeRequirementDefinition extends AttributeRequirementDefinition {

    private BasicAttribute attribute;

    public BasicAttribute getAttribute() {
        return attribute;
    }

    public static class BasicAttributeRequirementDefinitionBuilder {

        private final BasicAttributeRequirementDefinition basicAttributeRequirementDefinition = new BasicAttributeRequirementDefinition();

        public void setAttribute(BasicAttribute attribute) {
            basicAttributeRequirementDefinition.attribute = attribute;
        }

        public void setAmount(int amount) {
            basicAttributeRequirementDefinition.amount = amount;
        }

        public BasicAttributeRequirementDefinition build() {
            return basicAttributeRequirementDefinition;
        }
    }
}

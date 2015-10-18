package com.morethanheroic.swords.attribute.domain.requirement;

import com.morethanheroic.swords.attribute.domain.GeneralAttribute;

public class GeneralAttributeRequirementDefinition  extends AttributeRequirementDefinition {

    private GeneralAttribute attribute;

    public GeneralAttribute getAttribute() {
        return attribute;
    }

    public static class GeneralAttributeRequirementDefinitionBuilder {

        private final GeneralAttributeRequirementDefinition generalAttributeRequirementDefinition = new GeneralAttributeRequirementDefinition();

        public void setAttribute(GeneralAttribute attribute) {
            generalAttributeRequirementDefinition.attribute = attribute;
        }

        public void setAmount(int amount) {
            generalAttributeRequirementDefinition.amount = amount;
        }

        public GeneralAttributeRequirementDefinition build() {
            return generalAttributeRequirementDefinition;
        }
    }
}

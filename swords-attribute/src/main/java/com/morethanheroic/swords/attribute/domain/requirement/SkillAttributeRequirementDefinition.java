package com.morethanheroic.swords.attribute.domain.requirement;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;

public class SkillAttributeRequirementDefinition extends AttributeRequirementDefinition {

    private SkillAttribute attribute;

    public SkillAttribute getAttribute() {
        return attribute;
    }

    public static class SkillAttributeRequirementDefinitionBuilder {

        private final SkillAttributeRequirementDefinition basicAttributeRequirementDefinition = new SkillAttributeRequirementDefinition();

        public void setAttribute(SkillAttribute attribute) {
            basicAttributeRequirementDefinition.attribute = attribute;
        }

        public void setAmount(int amount) {
            basicAttributeRequirementDefinition.amount = amount;
        }

        public SkillAttributeRequirementDefinition build() {
            return basicAttributeRequirementDefinition;
        }
    }
}

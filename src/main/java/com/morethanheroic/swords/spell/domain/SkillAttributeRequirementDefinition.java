package com.morethanheroic.swords.spell.domain;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;

//TODO: WRONG! Check the item module how this should have been implemented.
public class SkillAttributeRequirementDefinition extends AttributeRequirementDefinition {

    private SkillAttribute attribute;

    public SkillAttribute getAttribute() {
        return attribute;
    }

    public static class SkillAttributeRequirementDefinitionBuilder {

        private final SkillAttributeRequirementDefinition skillAttributeRequirementDefinition = new SkillAttributeRequirementDefinition();

        public void setAttribute(SkillAttribute skillAttribute) {
            skillAttributeRequirementDefinition.attribute = skillAttribute;
        }

        public SkillAttributeRequirementDefinition build() {
            return skillAttributeRequirementDefinition;
        }
    }
}

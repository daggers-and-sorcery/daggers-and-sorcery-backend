package com.morethanheroic.swords.attribute.domain.modifier;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;

public class SkillAttributeModifierDefinition extends AttributeModifierDefinition {

    private SkillAttribute attribute;

    public SkillAttribute getAttribute() {
        return attribute;
    }

    public static class SkillAttributeModifierDefinitionBuilder {

        private final SkillAttributeModifierDefinition skillAttributeModifierDefinition = new SkillAttributeModifierDefinition();

        public void setAttribute(SkillAttribute attribute) {
            skillAttributeModifierDefinition.attribute = attribute;
        }

        public void setAmount(int amount) {
            skillAttributeModifierDefinition.amount = amount;
        }

        public SkillAttributeModifierDefinition build() {
            return skillAttributeModifierDefinition;
        }
    }
}
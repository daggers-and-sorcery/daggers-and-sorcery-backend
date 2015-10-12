package com.morethanheroic.swords.attribute.domain.modifier;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;

public class BasicAttributeModifierDefinition extends AttributeModifierDefinition {

    private BasicAttribute attribute;

    public BasicAttribute getAttribute() {
        return attribute;
    }

    public static class BasicAttributeModifierDefinitionBuilder {

        private final BasicAttributeModifierDefinition basicAttributeModifierDefinition = new BasicAttributeModifierDefinition();

        public void setAttribute(BasicAttribute attribute) {
            basicAttributeModifierDefinition.attribute = attribute;
        }

        public void setAmount(int amount) {
            basicAttributeModifierDefinition.amount = amount;
        }

        public BasicAttributeModifierDefinition build() {
            return basicAttributeModifierDefinition;
        }
    }
}

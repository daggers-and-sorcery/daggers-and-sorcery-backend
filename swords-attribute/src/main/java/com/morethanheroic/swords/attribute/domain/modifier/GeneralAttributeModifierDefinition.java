package com.morethanheroic.swords.attribute.domain.modifier;

import com.morethanheroic.swords.attribute.domain.GeneralAttribute;

public class GeneralAttributeModifierDefinition extends AttributeModifierDefinition {

    private GeneralAttribute attribute;

    public GeneralAttribute getAttribute() {
        return attribute;
    }

    public static class GeneralAttributeModifierDefinitionBuilder {

        private final GeneralAttributeModifierDefinition generalAttributeModifierDefinition = new GeneralAttributeModifierDefinition();

        public void setAttribute(GeneralAttribute attribute) {
            generalAttributeModifierDefinition.attribute = attribute;
        }

        public void setAmount(int amount) {
            generalAttributeModifierDefinition.amount = amount;
        }

        public GeneralAttributeModifierDefinition build() {
            return generalAttributeModifierDefinition;
        }
    }
}
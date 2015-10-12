package com.morethanheroic.swords.attribute.service;

import com.morethanheroic.swords.attribute.domain.modifier.BasicAttributeModifierDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawBasicAttributeModifierDefinition;

public class AttributeModifierTransformer {

    public BasicAttributeModifierDefinition transform(RawBasicAttributeModifierDefinition rawBasicAttributeModifierDefinition) {
        BasicAttributeModifierDefinition.BasicAttributeModifierDefinitionBuilder basicAttributeModifierDefinitionBuilder = new BasicAttributeModifierDefinition.BasicAttributeModifierDefinitionBuilder();

        basicAttributeModifierDefinitionBuilder.setAmount(rawBasicAttributeModifierDefinition.getAmount());
        basicAttributeModifierDefinitionBuilder.setAttribute(rawBasicAttributeModifierDefinition.getAttribute());

        return basicAttributeModifierDefinitionBuilder.build();
    }
}

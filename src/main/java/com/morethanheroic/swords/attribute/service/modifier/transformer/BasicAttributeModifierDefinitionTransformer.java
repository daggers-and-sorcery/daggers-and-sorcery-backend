package com.morethanheroic.swords.attribute.service.modifier.transformer;

import com.morethanheroic.swords.attribute.domain.modifier.BasicAttributeModifierDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawBasicAttributeModifierDefinition;
import org.springframework.stereotype.Service;

@Service
public class BasicAttributeModifierDefinitionTransformer {

    public BasicAttributeModifierDefinition transform(RawBasicAttributeModifierDefinition rawBasicAttributeModifierDefinition) {
        BasicAttributeModifierDefinition.BasicAttributeModifierDefinitionBuilder basicAttributeModifierDefinitionBuilder = new BasicAttributeModifierDefinition.BasicAttributeModifierDefinitionBuilder();

        basicAttributeModifierDefinitionBuilder.setAmount(rawBasicAttributeModifierDefinition.getAmount());
        basicAttributeModifierDefinitionBuilder.setAttribute(rawBasicAttributeModifierDefinition.getAttribute());

        return basicAttributeModifierDefinitionBuilder.build();
    }
}

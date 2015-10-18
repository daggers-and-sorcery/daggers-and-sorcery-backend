package com.morethanheroic.swords.attribute.service.modifier.transformer;

import com.morethanheroic.swords.attribute.domain.modifier.GeneralAttributeModifierDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawGeneralAttributeModifierDefinition;
import org.springframework.stereotype.Service;

@Service
public class GeneralAttributeModifierDefinitionTransformer {

    public GeneralAttributeModifierDefinition transform(RawGeneralAttributeModifierDefinition rawGeneralAttributeModifierDefinition) {
        GeneralAttributeModifierDefinition.GeneralAttributeModifierDefinitionBuilder generalAttributeModifierDefinitionBuilder = new GeneralAttributeModifierDefinition.GeneralAttributeModifierDefinitionBuilder();

        generalAttributeModifierDefinitionBuilder.setAmount(rawGeneralAttributeModifierDefinition.getAmount());
        generalAttributeModifierDefinitionBuilder.setAttribute(rawGeneralAttributeModifierDefinition.getAttribute());

        return generalAttributeModifierDefinitionBuilder.build();
    }
}

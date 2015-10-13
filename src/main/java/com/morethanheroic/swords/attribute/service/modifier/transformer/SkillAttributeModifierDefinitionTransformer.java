package com.morethanheroic.swords.attribute.service.modifier.transformer;

import com.morethanheroic.swords.attribute.domain.modifier.SkillAttributeModifierDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawSkillAttributeModifierDefinition;
import org.springframework.stereotype.Service;

@Service
public class SkillAttributeModifierDefinitionTransformer {

    public SkillAttributeModifierDefinition transform(RawSkillAttributeModifierDefinition rawSkillAttributeModifierDefinition) {
        SkillAttributeModifierDefinition.SkillAttributeModifierDefinitionBuilder skillAttributeModifierDefinitionBuilder = new SkillAttributeModifierDefinition.SkillAttributeModifierDefinitionBuilder();

        skillAttributeModifierDefinitionBuilder.setAmount(rawSkillAttributeModifierDefinition.getAmount());
        skillAttributeModifierDefinitionBuilder.setAttribute(rawSkillAttributeModifierDefinition.getAttribute());

        return skillAttributeModifierDefinitionBuilder.build();
    }
}

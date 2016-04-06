package com.morethanheroic.swords.spell.service.transformer;

import com.morethanheroic.swords.spell.domain.SkillAttributeRequirementDefinition;
import com.morethanheroic.swords.spell.service.loader.domain.RawSkillAttributeRequirementDefinition;
import org.springframework.stereotype.Service;

@Service("spellSkillAttributeRequirementDefinitionTransformer")
public class SkillAttributeRequirementDefinitionTransformer {

    public SkillAttributeRequirementDefinition transform(RawSkillAttributeRequirementDefinition rawSkillAttributeRequirementDefinition) {
        SkillAttributeRequirementDefinition.SkillAttributeRequirementDefinitionBuilder skillAttributeRequirementDefinitionBuilder = new SkillAttributeRequirementDefinition.SkillAttributeRequirementDefinitionBuilder();

        skillAttributeRequirementDefinitionBuilder.setAttribute(rawSkillAttributeRequirementDefinition.getAttribute());

        return skillAttributeRequirementDefinitionBuilder.build();
    }
}

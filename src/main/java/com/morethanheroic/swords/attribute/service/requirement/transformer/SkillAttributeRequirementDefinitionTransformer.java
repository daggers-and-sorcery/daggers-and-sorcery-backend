package com.morethanheroic.swords.attribute.service.requirement.transformer;

import com.morethanheroic.swords.attribute.domain.requirement.SkillAttributeRequirementDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawSkillAttributeRequirementDefinition;
import org.springframework.stereotype.Service;

@Service
public class SkillAttributeRequirementDefinitionTransformer {

    public SkillAttributeRequirementDefinition transform(RawSkillAttributeRequirementDefinition rawSkillAttributeRequirementDefinition) {
        SkillAttributeRequirementDefinition.SkillAttributeRequirementDefinitionBuilder skillAttributeRequirementDefinitionBuilder = new SkillAttributeRequirementDefinition.SkillAttributeRequirementDefinitionBuilder();

        skillAttributeRequirementDefinitionBuilder.setAmount(rawSkillAttributeRequirementDefinition.getAmount());
        skillAttributeRequirementDefinitionBuilder.setAttribute(rawSkillAttributeRequirementDefinition.getAttribute());

        return skillAttributeRequirementDefinitionBuilder.build();
    }
}
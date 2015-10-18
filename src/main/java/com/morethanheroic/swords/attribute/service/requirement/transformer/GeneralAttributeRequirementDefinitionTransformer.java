package com.morethanheroic.swords.attribute.service.requirement.transformer;

import com.morethanheroic.swords.attribute.domain.requirement.GeneralAttributeRequirementDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawGeneralAttributeRequirementDefinition;
import org.springframework.stereotype.Service;

@Service
public class GeneralAttributeRequirementDefinitionTransformer {

    public GeneralAttributeRequirementDefinition transform(RawGeneralAttributeRequirementDefinition rawGeneralAttributeRequirementDefinition) {
        GeneralAttributeRequirementDefinition.GeneralAttributeRequirementDefinitionBuilder generalAttributeRequirementDefinitionBuilder = new GeneralAttributeRequirementDefinition.GeneralAttributeRequirementDefinitionBuilder();

        generalAttributeRequirementDefinitionBuilder.setAmount(rawGeneralAttributeRequirementDefinition.getAmount());
        generalAttributeRequirementDefinitionBuilder.setAttribute(rawGeneralAttributeRequirementDefinition.getAttribute());

        return generalAttributeRequirementDefinitionBuilder.build();
    }
}

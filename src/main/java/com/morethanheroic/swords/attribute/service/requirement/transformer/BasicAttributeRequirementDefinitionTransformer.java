package com.morethanheroic.swords.attribute.service.requirement.transformer;

import com.morethanheroic.swords.attribute.domain.requirement.BasicAttributeRequirementDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawBasicAttributeRequirementDefinition;
import org.springframework.stereotype.Service;

@Service
public class BasicAttributeRequirementDefinitionTransformer {

    public BasicAttributeRequirementDefinition transform(RawBasicAttributeRequirementDefinition rawBasicAttributeRequirementDefinition) {
        BasicAttributeRequirementDefinition.BasicAttributeRequirementDefinitionBuilder basicAttributeRequirementDefinitionBuilder = new BasicAttributeRequirementDefinition.BasicAttributeRequirementDefinitionBuilder();

        basicAttributeRequirementDefinitionBuilder.setAmount(rawBasicAttributeRequirementDefinition.getAmount());
        basicAttributeRequirementDefinitionBuilder.setAttribute(rawBasicAttributeRequirementDefinition.getAttribute());

        return basicAttributeRequirementDefinitionBuilder.build();
    }
}
